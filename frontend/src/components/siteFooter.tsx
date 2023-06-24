import { siteConfig } from "@/config/site";
export function SiteFooter() {
    return (
      <footer className="py-6 md:px-8 md:py-0 fixed bottom-0 w-full bg-slate-200">
        <div className="container flex flex-col items-center justify-between gap-4 md:h-24 md:flex-row">
          <p className="text-center text-sm leading-loose text-gray-500 md:text-left">
            Built by{" "}
            <a
              href={siteConfig.links.personal_github}
              target="_blank"
              rel="noreferrer"
              className="font-medium underline underline-offset-4 text-blue-500"
            >
              saths008
            </a>
            . The source code is available on{" "}
            <a
              href={siteConfig.links.fareCompare_github}
              target="_blank"
              rel="noreferrer"
              className="font-medium underline underline-offset-4 text-blue-500"
            >
              GitHub
            </a>
            .
          </p>
        </div>
      </footer>
    );
  }
  