// @ts-nocheck 
"use client"
import { toast } from "sonner";
import { useEffect, useRef, useState } from "react";
import "primereact/resources/themes/lara-light-indigo/theme.css"; 
import "primereact/resources/primereact.min.css"; 
import { FileUpload } from 'primereact/fileupload';
import FareForm from "@/components/fareForm";
import { log } from "console";
import { Loader } from "lucide-react";
import nextConfig from "../../next.config";

export default  function Home() {
  const [fileData, setFileData] = useState("default file data");
  // @ts-ignore
  const [response, setResponse] = useState<Array<String>>(null);
  const [incorrectFileType, setincorrectFileType] = useState<String|null>(null)
  const [formAppear, setFormAppear] = useState(false);
  const [fileUploadSuccess, setFileUploadSuccess] = useState<boolean>(false);
  const handleBeforeUpload = (event: any) => {
    console.log('here')
    const file = event.files[0];
    // console.log(`event: ${JSON.stringify(event)}`)
    if (file.type !== 'text/calendar' && !file.name.endsWith('.ics')) {
      // Invalid file type

      console.log('Invalid file type. Only .ics files are allowed.');
      setincorrectFileType(`Invalid file type. Only .ics files are allowed. You uploaded a ${file.type}`);
      alert(`Invalid file type. Only .ics files are allowed. You uploaded a ${file.type}`)
      return;
    }
  };

  const handleFileUpload = async (event: any) => {
    const formData = new FormData();
    const file = event.files[0];
    formData.append('file', file);
    toast('Uploading file.....', {
      icon: <Loader />,
    });
      console.log(`SERVER_DOMAIN: ${nextConfig.publicRuntimeConfig.NEXT_PUBLIC_SERVER_DOMAIN}`);
      const responseMessage = await fetch( `${process.env.NEXT_PUBLIC_SERVER_DOMAIN}/api/v1/upload/local`, {
        method: 'POST',
        body: formData
      })
      

    const jsonResponse = await responseMessage.json();
    if (responseMessage.ok) {
      toast.success('File uploaded successfully');
      setResponse(jsonResponse.eventDetails);
      setFileData(jsonResponse.fileData);
    }
    else{
      toast.error(jsonResponse.error);
    }
    setFormAppear(responseMessage.ok);
    setFileUploadSuccess(responseMessage.ok);
  };
  return (
    <>  
        <div className="grid h-screen place-items-center">
            {!formAppear && (<FileUpload 
              mode = "basic"
              name="file" 
              customUpload = {true}
              accept=".ics"
              disabled={formAppear}
              maxFileSize={1000000} 
              uploadHandler={handleFileUpload}
              onSelect={handleBeforeUpload}
              />)}

        </div>
  
      <div>
        {formAppear && (<FareForm response={response} fileData={fileData}/>)}
      </div>
    </>
  )
}