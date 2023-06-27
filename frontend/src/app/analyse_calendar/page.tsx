// @ts-nocheck
"use client";
import { format } from "date-fns";
import { Calendar as CalendarIcon } from "lucide-react";
import CircularIndeterminate from "@/components/loadingIcon";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import { Calendar } from "@/components/ui/calendar";
import { toast } from "sonner";
import { useState } from "react";
import FareForm from "@/components/fareForm";
import { Loader } from "lucide-react";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import * as React from "react";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";

export function CardWithForm({ handleFileUpload, startDate, endDate }) {
  return (
    <Card className="w-[350px]">
      <CardHeader>
        <CardTitle>Journey</CardTitle>
        <CardDescription>Fill in your travel fare.</CardDescription>
      </CardHeader>
      <CardContent>
        <form onSubmit={handleFileUpload}>
          <input type="file" name="fileInput" accept=".ics" required />
          <DatePicker date={startDate} setDate={setStartDate} />
          <DatePicker date={endDate} setDate={setEndDate} />
          <button type="submit">Submit</button>
        </form>
      </CardContent>
      <CardFooter className="flex justify-between">
        <Button variant="outline">Cancel</Button>
        <Button>Deploy</Button>
      </CardFooter>
    </Card>
  );
}

export default function AnalyseCalendarPage() {
  const [fileData, setFileData] = useState("default file data");
  const [startDate, setStartDate] = useState<Date | undefined>(new Date());
  const [endDate, setEndDate] = useState<Date | undefined>(new Date());
  // @ts-ignore
  const [response, setResponse] = useState<Array<String>>(null);
  const [incorrectFileType, setincorrectFileType] = useState<String | null>(
    null
  );
  const [formAppear, setFormAppear] = useState(false);

  const handleFileUpload = async (event: any) => {
    event.preventDefault();

    const formData = new FormData(event.target);

    const file = event.target.elements.fileInput.files[0];
    formData.append("file", file);
    formData.append("startDate", startDate.toISOString());
    formData.append("endDate", endDate.toISOString());
    toast("Uploading file.....", {
      icon: <Loader className="animate-spin" />,
    });

    try {
      const responseMessage = await fetch(
        `${process.env.NEXT_PUBLIC_SERVER_DOMAIN}/api/v1/upload/local`,
        {
          method: "POST",
          body: formData,
        }
      );

      const jsonResponse = await responseMessage.json();
      if (responseMessage.ok) {
        toast.success("File uploaded successfully");
        setResponse(jsonResponse.eventDetails);
        setFileData(jsonResponse.fileData);
      } else {
        toast.error(jsonResponse.error);
      }
      setFormAppear(responseMessage.ok);
    } catch (error) {
      toast.error("Something went wrong. Please try again later");
    }
    event.target.reset();
  };
  return (
    <>
      <div className="grid place-items-center min-h-screen">
        {!formAppear && (
          <>
            <Card className="w-[350px]">
              <CardHeader>
                <CardTitle>Upload your Calendar</CardTitle>
                <CardDescription>
                  Upload your .ics file with the date range you want to analyse.
                </CardDescription>
              </CardHeader>
              <CardContent>
                <form onSubmit={handleFileUpload}>
                  <div className="pb-12">
                    <Input
                      type="file"
                      name="fileInput"
                      accept=".ics"
                      required
                    />
                  </div>
                  <div className="pb-12">
                    <DatePicker date={startDate} setDate={setStartDate} />
                  </div>
                  <div className="pb-12">
                    <DatePicker
                      date={endDate}
                      setDate={setEndDate}
                      className="pb-4"
                    />
                  </div>
                  <CardFooter className="flex justify-between pt-5">
                    <Button type="submit" variant="outline">
                      Submit
                    </Button>
                  </CardFooter>
                </form>
              </CardContent>
            </Card>
          </>
        )}
      </div>

      <div>
        {formAppear && response.length == 0 && (
          <b>Your selected date range had 0 events to be analysed.</b>
        )}
      </div>
      <div>
        {formAppear && response.length > 0 && (
          <FareForm
            response={response}
            fileData={fileData}
            startDate={startDate}
            endDate={endDate}
          />
        )}
      </div>
    </>
  );
}

function DatePicker({ date, setDate }) {
  return (
    <Popover>
      <PopoverTrigger asChild>
        <Button
          variant={"outline"}
          className={cn(
            "w-[280px] justify-start text-left font-normal",
            !date && "text-muted-foreground"
          )}
        >
          <CalendarIcon className="mr-2 h-4 w-4" />
          {date ? format(date, "PPP") : <span>Pick a date</span>}
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-auto p-0">
        <Calendar
          mode="single"
          selected={date}
          onSelect={setDate}
          initialFocus
          required
        />
      </PopoverContent>
    </Popover>
  );
}
