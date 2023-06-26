import {
  HoverCard,
  HoverCardContent,
  HoverCardTrigger,
} from "@/components/ui/hover-card";
import { Icons } from "@/components/icons";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import Link from "next/link";
import { Button } from "./ui/button";

interface HoverCardForGitHubProps {
  showIcon: boolean;
  linkTo: string;
  displayName?: string;
  summary: string;
  description: string;
}
export function HoverCardForGitHub({
  showIcon,
  linkTo,
  displayName,
  summary,
  description,
}: HoverCardForGitHubProps) {
  return (
    <HoverCard>
      <HoverCardTrigger asChild>
        <Link
          href={linkTo}
          target="_blank"
          rel="noopener noreferrer"
          className={
            showIcon
              ? "h-6 w-6"
              : "font-medium underline underline-offset-4 text-blue-500"
          }
        >
          {showIcon ? (
            <Button variant="ghost" size="icon">
              <Icons.gitHub
                className="h-5 w-5 rotate-0 scale-100 transition-all"
                aria-hidden="true"
              />
            </Button>
          ) : (
            <text>{displayName}</text>
          )}
        </Link>
      </HoverCardTrigger>
      <HoverCardContent className="w-80">
        <div className="flex justify-between space-x-4">
          <Avatar>
            <AvatarImage src="https://github.com/saths008.png" />
            <AvatarFallback>SS</AvatarFallback>
          </Avatar>
          <div className="space-y-1">
            <h4 className="text-sm font-semibold">{summary}</h4>
            <p className="text-sm">{description}</p>
          </div>
        </div>
      </HoverCardContent>
    </HoverCard>
  );
}
