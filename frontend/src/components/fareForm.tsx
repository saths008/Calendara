// "use client"
// import { Label } from "@/components/ui/label";
// import { Button } from "@/components/ui/button";
// import { Input } from "@nextui-org/react";
// import { useState } from "react";

// export default function FareForm({response, fileData}:any) {
//     const [fare, setFare] = useState(null);

//     async function handleSubmit(event: any) {
//         event.preventDefault();
      
//         const formData = new FormData(event.target);
//         const data = Object.fromEntries(Array.from(formData));
//         data.fileData = fileData;
//         console.log(`data: ${JSON.stringify(data)}`);
//         try {
//           const response = await fetch('http://localhost:8080/api/v1/submitForm', {
//             method: 'POST',
//             headers: {
//               'Content-Type': 'application/json',
//             },
//             body: JSON.stringify(data), // Convert FormData entries to JSON object
//           });
          
//           if (response.ok) {
//             // Form submission successful
//             const jsonResponse = await response.json();
//             setFare(jsonResponse.fare);
//             console.log('Form submitted successfully');
//           } else {
//             // Form submission failed
//             console.error('Form submission failed');
//           }
//         } catch (error) {
//           console.error('An error occurred:', error);
//         }
//       }

//     return (
//         <>
//         <p>How much does each journey cost?</p>
//       <form onSubmit={handleSubmit}>

//           <div className="h-56 grid grid-cols-3 gap-4 content-center">
//       {     response &&
//             response.map((element: string, index: number) => (
//                 <div key={index}>
//                   {/* <label htmlFor={`input-${index}`}>{element}</label> */}
//                   <Label htmlFor={`input-${index}`}>{element}</Label>
//                   {/* <input name= {element.toString()} id={`input-${index}`} type="text" /> */}
//                   <Input name= {element.toString()} id={`input-${index}`} type="text" />
//                 </div>
//             ))}
//         </div>
//             {fare && <p>Total Fare: {fare}</p>}
//              {/* <button type="submit">Submit</button> */}
//              <Button type="submit">Submit</Button>
//           </form>
//         </>
//     )
// }


"use client";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { Input } from "@nextui-org/react";
import { useState } from "react";

export default function FareForm({ response, fileData }: any) {
  const [fare, setFare] = useState(null);

  async function handleSubmit(event: any) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(Array.from(formData));
    data.fileData = fileData;
    console.log(`data: ${JSON.stringify(data)}`);
    try {
      const response = await fetch("http://localhost:8080/api/v1/submitForm", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data), // Convert FormData entries to JSON object
      });

      if (response.ok) {
        // Form submission successful
        const jsonResponse = await response.json();
        setFare(jsonResponse.fare);
        console.log("Form submitted successfully");
      } else {
        // Form submission failed
        console.error("Form submission failed");
      }
    } catch (error) {
      console.error("An error occurred:", error);
    }
  }

  return (
    <div className="flex flex-col items-center inset-x-0 top-2 absolute">
      <p className="text-center text-xl mb-4">How much does each journey cost?</p>
      <form onSubmit={handleSubmit}>
        <div className="grid grid-cols-2 gap-4">
          {response &&
            response.map((element: string, index: number) => (
              <div key={index} className="flex flex-col">
                <Label htmlFor={`input-${index}`} className="mb-1">
                  {element}
                </Label>
                <Input
                  name={element.toString()}
                  id={`input-${index}`}
                  type="number"
                  required
                  className="rounded-md border border-gray-300 px-2 py-1 focus:outline-none focus:border-blue-500"
                />
              </div>
            ))}
        </div>
        {fare && <p className="text-center mt-4">Total Fare: {fare}</p>}
        <div className="flex justify-center mt-4">
          <Button type="submit" className="bg-slate-600 hover:bg-zinc-600 text-white">
            Submit
          </Button>
        </div>
      </form>
    </div>
  );
}

