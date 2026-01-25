"use client"

import { useEffect, useState } from "react";

export default function TestComponent() {
    
    const [accounts, setAccounts] = useState<string>("");

    useEffect(() => {
        fetch('/account/api/v1/accounts', {
            credentials: 'include'
        })
        .then(res => res.json())
        .then(json => {
            console.log('Json', json);
            setAccounts(json.accounts);
        })
    }, [])

    return (
        <>
        <h2 className="text-red-900 font-bold">{accounts}</h2>
        </>
    )
}
