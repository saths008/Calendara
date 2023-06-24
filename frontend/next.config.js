/** @type {import('next').NextConfig} */
const nextConfig = {
    publicRuntimeConfig: {
        NEXT_PUBLIC_SERVER_DOMAIN: process.env.NEXT_PUBLIC_SERVER_DOMAIN,
    }
}

module.exports = nextConfig
