import Image from "next/image";
import { siteConfig } from "@/config/site";
import { buttonVariants } from "./ui/button";
import Link from "next/link";
import { cn } from "@/lib/utils";
export default function ProfileAvatar() {
  return (
    <>
      <Link href="/" className={cn(buttonVariants({ variant: "default" }))}>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          className="bi bi-suit-spade-fill"
          viewBox="0 0 16 16"
        >
          <path d="M7.184 11.246A3.5 3.5 0 0 1 1 9c0-1.602 1.14-2.633 2.66-4.008C4.986 3.792 6.602 2.33 8 0c1.398 2.33 3.014 3.792 4.34 4.992C13.86 6.367 15 7.398 15 9a3.5 3.5 0 0 1-6.184 2.246 19.92 19.92 0 0 0 1.582 2.907c.231.35-.02.847-.438.847H6.04c-.419 0-.67-.497-.438-.847a19.919 19.919 0 0 0 1.582-2.907z" />
        </svg>

        <div className="pl-2 pb-2 mb-2 mt-4 text-xl font-medium">
          {siteConfig.project_name}
        </div>
      </Link>
    </>
  );
}
