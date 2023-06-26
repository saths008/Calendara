import { Icons } from "@/components/icons";
import { buttonVariants } from "@/components/ui/button";
import {
  PageHeader,
  PageHeaderDescription,
  PageHeaderHeading,
} from "@/components/ui/page-header";
import { siteConfig } from "@/config/site";
import { cn } from "@/lib/utils";
import { ChevronRight } from "lucide-react";
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
        <Link
          target="_blank"
          rel="noreferrer"
          href={siteConfig.links.personal_github}
          className={cn(buttonVariants({ variant: "outline" }))}
        >
          <Icons.gitHub className="mr-2 h-4 w-4" />
          GitHub
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
