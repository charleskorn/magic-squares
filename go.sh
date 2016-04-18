#!/usr/bin/env bash

set -e

function main {
  case "$1" in

  build)
    build
    ;;

  test)
    test
    ;;

  run)
    run "${@:2}"
    ;;

  *)
    help
    exit 1
    ;;

  esac
}

function help {
  echo "Usage:"
  echo " build        builds the application"
  echo " test         runs the test suite"
  echo " run <args>   builds and runs the application (<args> are passed through to the application)"
}

function build {
  ./gradlew installDist
}

function test {
  ./gradlew cleanTest test
}

function run {
  echo "Building..."
  ./gradlew installDist --quiet

  echo "Running..."
  ./build/install/magic-squares/bin/magic-squares "$@"
}

main "$@"
