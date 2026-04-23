#!/usr/bin/env bash

set -euo pipefail

IMAGE_NAME="resultado-loteria:latest"
CONTAINER_NAME="resultado-loteria"
PORT="8080"

usage() {
  cat <<EOF
Usage: $0 {start|stop}

Commands:
  start   Build image and start container on port ${PORT}
  stop    Stop and remove container
EOF
}

stop_container() {
  if docker ps -a --format '{{.Names}}' | grep -Fxq "${CONTAINER_NAME}"; then
    docker stop "${CONTAINER_NAME}" >/dev/null
    docker rm "${CONTAINER_NAME}" >/dev/null
    echo "Container ${CONTAINER_NAME} stopped and removed."
  else
    echo "Container ${CONTAINER_NAME} does not exist."
  fi
}

start_container() {
  docker build -t "${IMAGE_NAME}" .
  stop_container || true
  docker run -d \
    --name "${CONTAINER_NAME}" \
    -p "${PORT}:${PORT}" \
    "${IMAGE_NAME}" >/dev/null

  echo "Container ${CONTAINER_NAME} started on http://localhost:${PORT}"
}

main() {
  if [ $# -ne 1 ]; then
    usage
    exit 1
  fi

  case "$1" in
    start)
      start_container
      ;;
    stop)
      stop_container
      ;;
    *)
      usage
      exit 1
      ;;
  esac
}

main "$@"
