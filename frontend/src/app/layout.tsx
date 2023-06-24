import './globals.css'
import { Inter } from 'next/font/google'
import type { Metadata } from "next"
import {Toaster} from '@/components/ui/toasterSonner'
import { SiteFooter } from '@/components/siteFooter'
const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title:"fareCompare",
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
    ".ics"
  ],
  authors: [
    {
      name: "saths008",
      url: "https://github.com/saths008",
    },
  ],
  creator: "saths008",
}


export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>        
        {children}
        <Toaster />
        <SiteFooter/>
      </body>
    </html>
  )
}
