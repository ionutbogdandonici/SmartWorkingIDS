import React, { Fragment } from "react";

import { Disclosure, Menu, Transition } from "@headlessui/react";
import { MenuIcon, XIcon } from "@heroicons/react/outline";

function classNames(...classes) {
    return classes.filter(Boolean).join(" ");
}

const Dashboard = ({ navigationLinks, userNavigationLinks, user }) => {
    return (
        <div className="min-h-full">
            <Disclosure as="nav" className="bg-green-600 sticky">
                {({ open }) => (
                    <>
                        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                            <div className="flex items-center justify-between h-16">
                                <div className="flex items-center">
                                    <div className="flex-shrink-0">
                                        <p className="font-bold text-white text-2xl">Cicerone App</p>
                                    </div>
                                    <div className="hidden md:block">
                                        <div className="ml-10 flex items-baseline space-x-4">
                                            {navigationLinks.map((item) => (
                                                <a key={item.name} href={item.href} className={classNames(item.current ? "bg-green-700 text-white" : "text-white hover:bg-green-500 hover:bg-opacity-75", "px-3 py-2 rounded-md text-sm font-medium")} aria-current={item.current ? "page" : undefined}>
                                                    {item.name}
                                                </a>
                                            ))}
                                        </div>
                                    </div>
                                </div>
                                <div className="hidden md:block">
                                    <div className="ml-4 flex items-center md:ml-6">
                                        {
                                            // TODO icona Notifiche --> branded_nav_with_compact_white_page_header.jsx [76-82]
                                        }
                                        <Menu as="div" className="ml-3 relative">
                                            <div>
                                                <Menu.Button className="max-w-xs bg-green-600 rounded-full flex items-center text-sm focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-green-600 focus:ring-white">
                                                    <span className="sr-only">Apri Menu Utente</span>
                                                    <img className="h-8 w-8 rounded-full" src="https://picsum.photos/200" alt="Profile_Image" />
                                                </Menu.Button>
                                            </div>
                                            <Transition as={Fragment} enter="transition ease-out duration-100" enterFrom="transform opacity-0 scale-95" enterTo="transform opacity-100 scale-100" leave="transition ease-in duration-75" leaveFrom="transform opacity-100 scale-100" leaveTo="transform opacity-0 scale-95">
                                                <Menu.Items className="origin-top-right absolute right-0 mt-2 w-48 rounded-md shadow-lg py-1 bg-white ring-1 ring-black ring-opacity-5 focus:outline-none">
                                                    {userNavigationLinks.map((item) => (
                                                        <Menu.Item key={item.name}>
                                                            {({ active }) => (
                                                                <a href={item.href} className={classNames(active ? "bg-gray-100" : "", "block px-2 py-2 text-sm text-gray-700")}>
                                                                    {item.name}
                                                                </a>
                                                            )}
                                                        </Menu.Item>
                                                    ))}
                                                </Menu.Items>
                                            </Transition>
                                        </Menu>
                                    </div>
                                </div>
                                <div className="-mr-2 flex md:hidden">
                                    <Disclosure.Button className="bg-green-600 inline-flex items-center justify-center p-2 rounded-md text-green-200 hover:text-white hover:bg-green-500 hover:bg-opacity-75 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-green-600 focus:ring-white">
                                        <span className="sr-only">Apri Menu</span>
                                        {open ? <XIcon className="block h-6 w-6" aria-hidden="true" /> : <MenuIcon className="block h-6 w-6" aria-hidden="true" />}
                                    </Disclosure.Button>
                                </div>
                            </div>
                        </div>
                        <Disclosure.Panel className="md:hidden">
                            <div className="px-2 pt-2 pb-3 space-y-1 sm:px-3">
                                {navigationLinks.map((item) => (
                                    <Disclosure.Button key={item.name} as="a" href={item.href} className={classNames(item.current ? "bg-green-700 text-white" : "text-white hover:bg-green-500 hover:bg-opacity-75", "block px-3 py-2 rounded-md text-base font-medium")} aria-current={item.current ? "page" : undefined}>
                                        {item.name}
                                    </Disclosure.Button>
                                ))}
                            </div>
                            <div className="pt-4 pb-3 border-t border-green-700">
                                <div className="flex items-center px-5">
                                    <div className="flex-shrink-0">
                                        <img className="h-10 w-10 rounded-full" src="https://picsum.photos/200" alt="Profile_Image"/>
                                    </div>
                                    <div className="ml-3">
                                        <div className="text-base font-medium text-white">{user.nome}</div>
                                        <div className="text-sm font-normal text-green-300">{user.email}</div>
                                    </div>
                                </div>
                                <div className="mt-3 px-2 space-y-1">
                                    {userNavigationLinks.map((item) => (
                                        <Disclosure.Button key={item.name} as="a" href={item.href} className="block px-3 py-2 rounded-md text-base font-medium text-white hover:bg-green-500 hover:bg-opacity-75">
                                            {item.name}
                                        </Disclosure.Button>
                                    ))}
                                </div>
                            </div>
                        </Disclosure.Panel>
                    </>
                )}
            </Disclosure>
            
        </div>
    );
};

export default Dashboard;
