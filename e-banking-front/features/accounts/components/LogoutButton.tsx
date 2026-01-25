'use client';

import { Button } from "@/components/ui/button";

export default function LogoutButton() {
    const handleLogout = async () => {
        try {
            const response = await fetch('/logout', {
                method: 'POST',  
                credentials: 'include',
            });

            if (response.ok || response.redirected) {
                // Force a hard reload to clear everything
                window.location.href = '/';
            }
        } catch (error) {
            console.error('Logout failed:', error);
        }
    };

    return (
        <Button 
            variant="outline" 
            size="sm"
            onClick={handleLogout}
        >
            Logout
        </Button>
    );
}