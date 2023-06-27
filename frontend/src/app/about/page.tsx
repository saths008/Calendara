import {
  PageHeader,
  PageHeaderDescription,
  PageHeaderHeading,
} from "@/components/ui/page-header";

function AboutContents() {
  return (
    <PageHeader className="pb-8">
      <PageHeaderHeading>About</PageHeaderHeading>
      <PageHeaderDescription>
        Calendara allows you to upload your calendar. It will return a form for
        you to fill out to gather travel fares then will output a total fare.
        Other features such as event frequency are in the making too.
      </PageHeaderDescription>
      Calendara doesn&#39;t store your calendar data. It is only used to
      calculate your travel fare.
    </PageHeader>
  );
}
export default function About() {
  return (
    <>
      <div className="grid place-items-center min-h-screen">
        <AboutContents />
      </div>
    </>
  );
}
