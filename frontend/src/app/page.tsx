import { Icons } from "@/components/icons";
import React from "react";
import { buttonVariants } from "@/components/ui/button";
import {
  PageHeader,
  PageHeaderDescription,
  PageHeaderHeading,
} from "@/components/ui/page-header";
import { siteConfig } from "@/config/site";
import { cn } from "@/lib/utils";
import Link from "next/link";
function WelcomeMessage() {
  return (
    <PageHeader className="pb-8">
      <PageHeaderHeading>fareCompare.</PageHeaderHeading>
      <PageHeaderDescription>
        Analyse your calendar to calculate travel costs.
      </PageHeaderDescription>
      <div className="flex w-full items-center space-x-4 pb-8 pt-4 md:pb-10">
        <Link
          href="/analyse_calendar"
          className={cn(buttonVariants({ variant: "outline" }))}
        >
          Get Started
        </Link>
      </div>
    </PageHeader>
  );
}
export default function Home() {
  return (
    <>
      <div className="grid place-items-center min-h-screen">
        <WelcomeMessage />
      </div>
    </>
  );
}
