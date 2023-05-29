"use client"
import { GetServerSideProps } from "next";
import { useState } from "react";
import "primereact/resources/themes/lara-light-indigo/theme.css"; 
import "primereact/resources/primereact.min.css"; 
import { FileUpload } from 'primereact/fileupload';
                 
export default function Home() {
  return (
    <>
    <h1>hello</h1>
    <FileUpload name="file" url={'http://localhost:8080/api/v1/upload/local'} multiple accept="image/*" maxFileSize={1000000} emptyTemplate={<p className="m-0">Drag and drop files to here to upload.</p>} />
    </>
  )
}