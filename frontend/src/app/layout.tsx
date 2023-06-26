import "./globals.css";
import { Inter } from "next/font/google";
import type { Metadata } from "next";
import { Toaster } from "@/components/ui/toasterSonner";
import { SiteFooter } from "@/components/siteFooter";
import NavBar from "@/components/navbar/navbar";
const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "fareCompare",
  description: "Transport Fare Calculator based on your calendar",
  keywords: [
    "Next.js",
    "React",
    "Tailwind CSS",
    "Calendar",
    "Transport",
    "Cost",
    "Fare",
    "Calculator",
    "Transport Fare Calculator",
    ".ics",
  ],
  authors: [
    {
      name: "saths008",
      url: "https://github.com/saths008",
    },
  ],
  creator: "saths008",
  icons: {
    icon: "/favicon.ico",
    shortcut: "/favicon-16x16.png",
    apple: "/apple-touch-icon.png",
  },
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  if (process.env.NEXT_PUBLIC_SERVER_DOMAIN == undefined) {
    throw new Error(
      "NEXT_PUBLIC_SERVER_DOMAIN is undefined. Please add it to your .env.local"
    );
  }
  return (
    <html lang="en">
      <body className={inter.className}>
        <NavBar />
        {children}
        <Toaster />
        <SiteFooter />
      </body>
    </html>
  );
}
