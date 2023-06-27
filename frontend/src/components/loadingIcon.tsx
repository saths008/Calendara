import { Loader } from "lucide-react";
export default function LoadingPage() {
  return (
    <div className="grid place-items-center min-h-screen">
      <Loader className="animate-spin h-32 w-32" />
    </div>
  );
}
