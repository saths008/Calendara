"use client"

import { Toaster as RadToaster } from "sonner"

export function Toaster() {
  return (
    <RadToaster
      richColors
      position="top-left"
      closeButton
    />
  )
}