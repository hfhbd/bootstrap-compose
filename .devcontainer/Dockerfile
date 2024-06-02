FROM mcr.microsoft.com/devcontainers/java:21

RUN apt-get update && apt-get install chromium -y
ENV CHROMIUM_BIN /usr/bin/chromium
ENV ORG_GRADLE_PROJECT_kotlin.js.browser.karma.browsers=chromium-headless
