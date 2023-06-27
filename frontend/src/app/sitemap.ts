import { MetadataRoute } from "next";

export default function sitemap(): MetadataRoute.Sitemap {
  return [
    {
      url: "https://calendara.vercel.app/",
      lastModified: new Date(),
    },
    {
      url: "https://calendara.vercel.app/about",
      lastModified: new Date(),
    },
    {
      url: "https://calendara.vercel.app/analyse_calendar",
      lastModified: new Date(),
    },
  ];
}
