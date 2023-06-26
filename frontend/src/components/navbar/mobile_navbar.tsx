"use client";
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet";
import {
  Accordion,
  AccordionNoTrigger,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
import { Button, buttonVariants } from "@/components/ui/button";
import Link from "next/link";
import { useState } from "react";
import { ScrollArea } from "@/components/ui/scroll-area";
import { ChevronsRightLeft } from "lucide-react";
import ProfileAvatar from "../profile_avatar";
import { HoverCardForGitHub } from "../HoverCardForGitHub";
import { siteConfig } from "@/config/site";
import { cn } from "@/lib/utils";

export function AccordionNavContent() {
  return (
    <Accordion type="single" collapsible className="w-full">
      <AccordionItem value="item-1">
        <AccordionNoTrigger>
          <Link href="/analyse_calendar" className="w-full">
            Analyse Calendar
          </Link>
        </AccordionNoTrigger>
      </AccordionItem>
      <AccordionItem value="item-2">
        <AccordionNoTrigger>
          <Link href="/about" className="w-full">
            About
          </Link>
        </AccordionNoTrigger>
      </AccordionItem>
    </Accordion>
  );
}
export default function MobileNavBar() {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <div className="lg:hidden">
      <nav className="flex items-center justify-between px-4 py-2">
        <Sheet open={isOpen} onOpenChange={setIsOpen}>
          <SheetTrigger asChild>
            <Button
              variant="ghost"
              className="mr-2 px-0 text-base hover:bg-transparent focus-visible:bg-transparent focus-visible:ring-0 focus-visible:ring-offset-0 lg:hidden"
            >
              <ChevronsRightLeft className="h-6 w-6" />
              <span className="sr-only">Toggle Menu</span>
            </Button>
          </SheetTrigger>
          <SheetContent side="left" className="pl-1 pr-0">
            <div className="px-7">
              <ProfileAvatar />
            </div>
            <ScrollArea className="my-4 h-[calc(100vh-8rem)] pb-10 pl-6 pr-6">
              <AccordionNavContent />
            </ScrollArea>
          </SheetContent>
        </Sheet>
        <div className="ml-auto">
          <HoverCardForGitHub
            showIcon={true}
            linkTo={siteConfig.links.fareCompare_github}
            summary="@saths008/fareCompare"
            description="fareCompare GitHub Repository"
          />
        </div>
      </nav>
    </div>
  );
}
