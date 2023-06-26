import MobileNavBar from "./mobile_navbar";
import { DesktopNavBar } from "./main_navbar";
export default function NavBar() {
  return (
    <>
      <MobileNavBar />
      <DesktopNavBar />
    </>
  );
}
