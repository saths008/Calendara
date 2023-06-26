import { siteConfig } from "@/config/site";
import { HoverCardForGitHub } from "./HoverCardForGitHub";

export function SiteFooter() {
  return (
    <footer className="bg-gray-200 rounded-lg shadow m-4 dark:bg-gray-800">
      <span className="text-sm text-gray-500">
        <div className="p-4">
          <p className="text-sm leading-loose text-gray-500 dark:text-white text-center pr-8">
            Built by{" "}
            <HoverCardForGitHub
              showIcon={false}
              linkTo={siteConfig.links.personal_github}
              displayName="Saath Satheeshkumar"
              summary="@saths008"
              description="Saath Satheeshkumars&#39; GitHub Account"
            />
            . The source code is available on{" "}
            <HoverCardForGitHub
              showIcon={false}
              linkTo={siteConfig.links.fareCompare_github}
              displayName="GitHub"
              summary="@saths008/fare-compare"
              description="fareCompare GitHub Repository"
            />
            .
          </p>
        </div>
      </span>
    </footer>
  );
}
