import { Button } from "@/components/ui/button";
import TestComponent from "@/features/accounts/components/AccountApi";
import LogoutButton from "@/features/accounts/components/LogoutButton";

export default function Page() {
    return (
        <>
            <h1>Dashboard Overview</h1>
            <TestComponent />
        </>
    )
}