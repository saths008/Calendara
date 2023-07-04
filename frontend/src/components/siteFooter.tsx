import { siteConfig } from "@/config/site";
import { HoverCardForGitHub } from "./HoverCardForGitHub";

export function SiteFooter() {
  const projectName = siteConfig.project_name;
  const projectAndUserName = `@${siteConfig.username}/${projectName}`;
  const descriptionForHoverCard = `${projectName} GitHub Repository`;
  return (
    <footer className="bg-gray-200 rounded-lg shadow m-4 dark:bg-gray-800">
      <span className="text-sm text-gray-500">
        <div className="p-4">
          <p className="text-sm leading-loose text-gray-500 dark:text-white text-center pr-8">
            Built by{" "}
            <HoverCardForGitHub
              showIcon={false}
              linkTo={siteConfig.links.personal_website}
              displayName="Saath Satheeshkumar"
              summary="My Portfolio"
              description="Saath Satheeshkumar's personal website"
            />
            . The source code is available on{" "}
            <HoverCardForGitHub
              showIcon={false}
              linkTo={siteConfig.links.project_github}
              displayName="GitHub"
              summary={projectAndUserName}
              description={descriptionForHoverCard}
            />
            .
          </p>
        </div>
      </span>
    </footer>
  );
}
