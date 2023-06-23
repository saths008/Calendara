"use client"
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { Input } from "@nextui-org/react";
import { useState } from "react";

export default function FareForm({response, fileData}:any) {
    const [fare, setFare] = useState(null);

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
            const jsonResponse = await response.json();
            setFare(jsonResponse.fare);
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
        <p>How much does each journey cost?</p>
      <form onSubmit={handleSubmit}>
      { 
            response.map((element: string, index: number) => (
                <div key={index}>
                  {/* <label htmlFor={`input-${index}`}>{element}</label> */}
                  <Label htmlFor={`input-${index}`}>{element}</Label>
                  {/* <input name= {element.toString()} id={`input-${index}`} type="text" /> */}
                  <Input name= {element.toString()} id={`input-${index}`} type="text" />
                </div>
            ))}

            {fare && <p>Total Fare: {fare}</p>}
             {/* <button type="submit">Submit</button> */}
             <Button type="submit">Submit</Button>
          </form>
        </>
    )
}