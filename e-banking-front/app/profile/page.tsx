"use client";

import { useEffect, useState } from "react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Badge } from "@/components/ui/badge";
import { Skeleton } from "@/components/ui/skeleton";
import { Mail, Phone, User, Shield } from "lucide-react";

interface ProfileData {
  uuid: string;
  username: string;
  email: string;
  fullName: string;
  familyName: string;
  givenName: string;
  phoneNumber: string;
  profileImage: string;
  gender: string;
  authorities: string[];
}

export default function ProfilePage() {
  const [profile, setProfile] = useState<ProfileData | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function fetchProfile() {
      try {
        const response = await fetch("/auth/profile", {
          credentials: "include",
        });

        if (!response.ok) {
          throw new Error("Failed to fetch profile");
        }

        const data = await response.json();
        setProfile(data);
      } catch (err) {
        setError(err instanceof Error ? err.message : "An error occurred");
      } finally {
        setLoading(false);
      }
    }

    fetchProfile();
  }, []);

  if (loading) {
    return (
      <div className="container mx-auto py-8 max-w-4xl">
        <Card>
          <CardHeader>
            <Skeleton className="h-8 w-48 mb-2" />
            <Skeleton className="h-4 w-64" />
          </CardHeader>
          <CardContent>
            <div className="space-y-4">
              <Skeleton className="h-24 w-24 rounded-full" />
              <Skeleton className="h-4 w-full" />
              <Skeleton className="h-4 w-full" />
              <Skeleton className="h-4 w-3/4" />
            </div>
          </CardContent>
        </Card>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mx-auto py-8 max-w-4xl">
        <Card className="border-destructive">
          <CardHeader>
            <CardTitle className="text-destructive">Error</CardTitle>
            <CardDescription>{error}</CardDescription>
          </CardHeader>
        </Card>
      </div>
    );
  }

  if (!profile) {
    return (
      <div className="container mx-auto py-8 max-w-4xl">
        <Card>
          <CardHeader>
            <CardTitle>No Profile Data</CardTitle>
            <CardDescription>Please login to view your profile</CardDescription>
          </CardHeader>
        </Card>
      </div>
    );
  }

  const getInitials = () => {
    if (profile.givenName && profile.familyName) {
      return `${profile.givenName[0]}${profile.familyName[0]}`.toUpperCase();
    }
    return profile.username?.substring(0, 2).toUpperCase() || "U";
  };

  return (
    <div className="container mx-auto py-8 max-w-4xl">
      <Card>
        <CardHeader>
          <CardTitle>Profile Information</CardTitle>
          <CardDescription>Your account details and information</CardDescription>
        </CardHeader>
        <CardContent className="space-y-6">
          {/* Avatar Section */}
          <div className="flex items-center space-x-4">
            <Avatar className="h-24 w-24">
              <AvatarImage src={profile.profileImage} alt={profile.fullName} />
              <AvatarFallback className="text-2xl">{getInitials()}</AvatarFallback>
            </Avatar>
            <div>
              <h2 className="text-2xl font-bold">{profile.fullName}</h2>
              <p className="text-muted-foreground">@{profile.username}</p>
            </div>
          </div>

          {/* Personal Information */}
          <div className="grid gap-4 md:grid-cols-2">
            <div className="space-y-2">
              <div className="flex items-center space-x-2 text-sm">
                <User className="h-4 w-4 text-muted-foreground" />
                <span className="font-semibold">First Name:</span>
                <span className="font-semibold text-red-900">{profile.givenName}</span>
              </div>
              <div className="flex items-center space-x-2 text-sm">
                <User className="h-4 w-4 text-muted-foreground" />
                <span className="font-semibold">Last Name:</span>
                <span className="">{profile.familyName}</span>
              </div>
              <div className="flex items-center space-x-2 text-sm">
                <Mail className="h-4 w-4 text-muted-foreground" />
                <span className="font-semibold">Email:</span>
                <span>{profile.email}</span>
              </div>
            </div>

            <div className="space-y-2">
              <div className="flex items-center space-x-2 text-sm">
                <Phone className="h-4 w-4 text-muted-foreground" />
                <span className="font-semibold">Phone:</span>
                <span>{profile.phoneNumber}</span>
              </div>
              <div className="flex items-center space-x-2 text-sm">
                <User className="h-4 w-4 text-muted-foreground" />
                <span className="font-semibold">Gender:</span>
                <span>{profile.gender}</span>
              </div>
              <div className="flex items-center space-x-2 text-sm">
                <Shield className="h-4 w-4 text-muted-foreground" />
                <span className="font-semibold">UUID:</span>
                <span className="text-xs font-mono">{profile.uuid}</span>
              </div>
            </div>
          </div>

          {/* Authorities/Roles */}
          {profile.authorities && profile.authorities.length > 0 && (
            <div className="space-y-2">
              <h3 className="text-sm font-semibold flex items-center space-x-2">
                <Shield className="h-4 w-4" />
                <span>Roles & Permissions</span>
              </h3>
              <div className="flex flex-wrap gap-2">
                {profile.authorities.map((authority, index) => (
                  <Badge key={index} variant="secondary">
                    {authority}
                  </Badge>
                ))}
              </div>
            </div>
          )}
        </CardContent>
      </Card>
    </div>
  );
}