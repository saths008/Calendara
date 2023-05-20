import { FileUpload } from "@/components/ui/FileUpload";
export default async function Home() {
  const res = await fetch("http://localhost:8080/", {
    method:"GET",
    headers:{"Content-Type": "application/json"},
    cache: 'no-store'
  })
  const data = await res.json();
  console.log(`data: ${JSON.stringify(data)}`)
  return (<>
  
  <p> If message appears, server is running correctly: {JSON.stringify(data.message)}</p>
  
  <FileUpload/>
  </>)    
}
