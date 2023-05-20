export default async function Home() {

  const res = await fetch("http://localhost:8080/", {
    method:"GET",
    headers:{"Content-Type": "application/json"},
    cache: 'no-store'
  })
  const data = await res.json();
  console.log(`data: ${JSON.stringify(data)}`)
  return (
    <>
    <h1>{JSON.stringify(data.message)}</h1>
    </>
  )
}
