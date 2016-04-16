#!/usr/bin/env bash

set -e

function main {
  case "$1" in

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
  echo " test         runs the test suite"
  echo " run <args>   builds and runs the application (<args> are passed through to the application)"
}

function test {
  ./gradlew cleanTest test
}

function run {
  ./gradlew installDist --quiet
  ./build/install/magic-squares/bin/magic-squares "$@"
}

main "$@"
