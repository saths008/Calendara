"use client";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { Input } from "@nextui-org/react";
import { useState } from "react";
import { Loader } from "lucide-react";
import { toast } from "sonner";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
export default function FareForm({
  response,
  fileData,
  startDate,
  endDate,
}: any) {
  const [fare, setFare] = useState(null);

  async function handleSubmit(event: any) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(Array.from(formData));
    data.fileData = fileData;
    data.startDate = startDate;
    data.endDate = endDate;
    console.log(`data: ${JSON.stringify(data)}`);
    try {
      toast("Uploading file.....", {
        icon: <Loader />,
      });
      const response = await fetch(
        `${process.env.NEXT_PUBLIC_SERVER_DOMAIN}/api/v1/submitForm`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data), // Convert FormData entries to JSON object
        }
      );

      if (response.ok) {
        // Form submission successful
        toast.success("Form submission successful");
        const jsonResponse = await response.json();
        setFare(jsonResponse.fare);
        console.log("Form submitted successfully");
      } else {
        // Form submission failed
        toast.error("Form submission failed");
        console.error("Form submission failed");
      }
    } catch (error) {
      toast.error("Something went wrong. Please try again later");
    }
  }

  return (
    <>
      {!fare && (
        <>
          <Card className="w-10/12">
            <CardHeader>
              <CardTitle>Last Step...</CardTitle>
              <CardDescription>
                Fill out the travel fare for each of these journeys.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <form onSubmit={handleSubmit}>
                <div className="grid grid-cols-2 gap-4">
                  {response &&
                    response.map((element: string, index: number) => (
                      <div key={index} className="flex flex-col">
                        <Label htmlFor={`input-${index}`} className="mb-1">
                          {element}
                        </Label>

                        <div className="display: inline-block">
                          <p>£</p>
                          <Input
                            name={element.toString()}
                            id={`input-${index}`}
                            type="number"
                            step="0.01"
                            min="0"
                            required
                            className="rounded-md border border-gray-300 px-2 py-1 focus:outline-none focus:border-blue-500"
                          />
                        </div>
                      </div>
                    ))}
                </div>
                <div className="flex justify-center mt-4"></div>
                <CardFooter className="flex justify-between pt-5">
                  <Button
                    type="submit"
                    variant="outline"
                    className="bg-slate-600 hover:bg-zinc-600 text-white"
                  >
                    Submit
                  </Button>
                </CardFooter>
              </form>
            </CardContent>
          </Card>
        </>
      )}

      {fare && <p className="text-center mt-4">Total Fare: {fare}</p>}
    </>
  );
}
