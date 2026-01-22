"use client";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";
import { Star } from "lucide-react";
import { useEffect, useState } from "react";

type CustomerType = {
  customerNo: string,
  firstName: string,
  lastName: string,
  email: string
};

export default function TestimonialSection() {

  const [customers, setCustomers] = useState<CustomerType[]>();

  useEffect(() => {

    function fetchCustomers() {
      fetch("/customer-service/api/v1/customers")
      .then (res => res.json())
      .then (json => {
        console.log("Customers:", json)
        setCustomers(json)
      })
    }

    fetchCustomers();

  }, []);


  return (
    <section id="testimonials" className="container py-24 sm:py-32 m-auto">
      <div className="text-center mb-8">
        <h2 className="text-lg text-amber-500 text-center mb-2 tracking-wider">
          Testimonials
        </h2>

        <h2 className="text-3xl md:text-4xl text-center font-bold mb-4">
          Hear What Our 1000+ Clients Say
        </h2>
      </div>

      <Carousel
        opts={{
          align: "start",
        }}
        className="relative w-[80%] sm:w-[90%] lg:max-w-screen-xl mx-auto"
      >
        <CarouselContent>
          {customers && customers.map((customer) => (
            <CarouselItem
              key={customer.customerNo}
              className="md:basis-1/2 lg:basis-1/3"
            >
              <Card className="bg-muted/50 dark:bg-card">
                <CardContent className="pt-6 pb-0">
                  <div className="flex gap-1 pb-6">
                    <Star className="size-4 fill-amber-500 text-amber-500" />
                    <Star className="size-4 fill-amber-500 text-amber-500" />
                    <Star className="size-4 fill-amber-500 text-amber-500" />
                    <Star className="size-4 fill-amber-500 text-amber-500" />
                    <Star className="size-4 fill-amber-500 text-amber-500" />
                  </div>
                  Wow NextJs + Shadcn is awesome!. This template lets me change colors, fonts and images to match my brand identity. 
                </CardContent>

                <CardHeader>
                  <div className="flex flex-row items-center gap-4">
                    <Avatar>
                      <AvatarImage
                        src="https://avatars.githubusercontent.com/u/75042455?v=4"
                        alt="radix"
                      />
                      <AvatarFallback>SV</AvatarFallback>
                    </Avatar>

                    <div className="flex flex-col">
                      <CardTitle className="text-lg">{customer.firstName + " " + customer.lastName}</CardTitle>
                      <CardDescription>{customer.email}</CardDescription>
                    </div>
                  </div>
                </CardHeader>
              </Card>
            </CarouselItem>
          ))}
        </CarouselContent>
        <CarouselPrevious />
        <CarouselNext />
      </Carousel>
    </section>
  );
};