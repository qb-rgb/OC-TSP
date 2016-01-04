#!/bin/sh

echo "[approx.jar] Compiling..."
sbt approx:assembly
echo "[approx.jar] Done!"
mv target/scala-2.10/approx.jar .
echo "The runnable jar approx.jar is in the project root directory."
