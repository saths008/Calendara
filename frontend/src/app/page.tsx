"use client"
import { GetServerSideProps } from "next";
import { useEffect, useState } from "react";
import "primereact/resources/themes/lara-light-indigo/theme.css"; 
import "primereact/resources/primereact.min.css"; 
import { FileUpload } from 'primereact/fileupload';
                 

export default  function Home() {
  const [welcome, setWelcome] = useState("default welcome")
  const [fileData, setFileData] = useState("default file data");
  // @ts-ignore
  const [response, setResponse] = useState<Array<String>>(null);
  const [incorrectFileType, setincorrectFileType] = useState<String|null>(null)
  const [formAppear, setFormAppear] = useState(false);
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
    console.log(`ln33: ${JSON.stringify(jsonResponse)}`);
    console.log(`ln34: ${JSON.stringify(jsonResponse.eventDetails)}`);
    setResponse(jsonResponse.eventDetails);
    setFileData(jsonResponse.fileData);
    console.log(`response after setting: ${response}`);
    setFormAppear(responseMessage.ok);
  };


  async function handleSubmit(event: any) {
    event.preventDefault();
  
    const formData = new FormData(event.target);
    const data = Object.fromEntries(Array.from(formData));
    data.fileData = fileData;
    console.log(`data: ${JSON.stringify(data)}`);
    try {
      const response = await fetch('http://localhost:8080/api/v1/submitForm', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data), // Convert FormData entries to JSON object
      });
  
      if (response.ok) {
        // Form submission successful
        console.log('Form submitted successfully');
      } else {
        // Form submission failed
        console.error('Form submission failed');
      }
    } catch (error) {
      console.error('An error occurred:', error);
    }
  }
  
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

      <div>
        <p>How much does each journey cost?</p>
      <form onSubmit={handleSubmit}>
      {formAppear &&
            response.map((element, index) => (
                <div key={index}>
                  <label htmlFor={`input-${index}`}>{element}</label>
                  <input name= {element.toString()} id={`input-${index}`} type="text" />
                </div>
            ))}
             <button type="submit">Submit</button>
          </form>
      </div>



    </>
  )
}