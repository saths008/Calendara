"use client"
import { GetServerSideProps } from "next";
import { useEffect, useState } from "react";
import "primereact/resources/themes/lara-light-indigo/theme.css"; 
import "primereact/resources/primereact.min.css"; 
import { FileUpload } from 'primereact/fileupload';
                 
export default  function Home() {
  const [welcome, setWelcome] = useState("default welcome")
  const [response, setResponse] = useState("default response")
  const [incorrectFileType, setincorrectFileType] = useState<String|null>(null)
  const welcomeMessage = async (event: any) => {
    try {
      const responseAPI = await fetch('http://localhost:8080/', {
        method: 'GET',
      });
      const jsonData = await responseAPI.json();
      const welcome = jsonData.message;
      setWelcome(welcome);
    } catch (error) {
      console.error('Error:', error);
    }
  };
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

    const responseMessage = await fetch('http://localhost:8080/api/v1/upload/local', {
      method: 'POST',
      body: formData
    })
    const jsonResponse = await responseMessage.json();
    console.log(`ln33: ${jsonResponse}`);
    console.log(`ln34: ${JSON.stringify(jsonResponse)}`);
    setResponse(JSON.stringify(jsonResponse));
    console.log(`response after setting: ${response}`);
  };
  return (
    <>
    <FileUpload 
    name="file" 
    customUpload = {true}
    multiple
    accept=".ics"
    maxFileSize={1000000} 
    emptyTemplate={<p className="m-0">Drag and drop files to here to upload.</p>} 
    onUpload={welcomeMessage}
    uploadHandler={handleFileUpload}
    onSelect={handleBeforeUpload}
    />
    {(
          <div>
            <h3>welcome:</h3>
            <p>{welcome}</p>
          </div>
        )}
        <h1>hello</h1>
    </>
  )
}