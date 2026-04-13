#!/usr/bin/env sh

set -e

COMMONS_NAME=spring-base-commons
COMMONS_GROUP_ID=com.vulinh
COMMONS_VERSION=2.5.0
GITHUB_USER=vulinh64

JAR_FILE="${COMMONS_NAME}-${COMMONS_VERSION}.jar"
DOWNLOAD_URL="https://github.com/${GITHUB_USER}/${COMMONS_NAME}/releases/download/${COMMONS_VERSION}/${JAR_FILE}"

# Create build directory if it doesn't exist
mkdir -p ./build

# Download the JAR file
echo "Downloading ${JAR_FILE}..."
curl -L -o "./build/${JAR_FILE}" "${DOWNLOAD_URL}"

# Clean the local .m2 repository folder if it exists
M2_PATH="${HOME}/.m2/repository/$(echo ${COMMONS_GROUP_ID} | tr '.' '/')/${COMMONS_NAME}/${COMMONS_VERSION}"

if [ -d "${M2_PATH}" ]; then
    echo "Cleaning existing Maven repository folder..."
    rm -rf "${M2_PATH}"
fi

# Ensure mvnw is executable
chmod +x ./mvnw

# Install the JAR to local Maven repository
echo "Installing ${JAR_FILE} to local Maven repository..."
./mvnw install:install-file \
    -Dfile="./build/${JAR_FILE}" \
    -DgroupId="${COMMONS_GROUP_ID}" \
    -DartifactId="${COMMONS_NAME}" \
    -Dversion="${COMMONS_VERSION}" \
    -Dpackaging=jar

echo "Successfully installed ${COMMONS_NAME} version ${COMMONS_VERSION}"