#!/usr/bin/env bash
set -e
SKIP="-Dgpg.skip=true -Dmaven.javadoc.skip=true -Dmaven.source.skip=true -DskipTests -Dmaven.test.skip=true"
echo "Installing dependencies..."
mvn -f temper-core/pom.xml install $SKIP -q
mvn -f snake/pom.xml install $SKIP -q
echo "Running snake..."
mvn -f snake-game/pom.xml compile exec:java@snake_game.SnakeGameMain $SKIP -q
