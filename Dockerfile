FROM amazoncorretto:21

WORKDIR /app

RUN mkdir -p bin

COPY src/ ./src/
COPY lib/ ./lib/

RUN javac -d bin -cp "lib/*" src/*.java

ENV CLASSPATH=/app/bin:/app/lib/

CMD ["java", "-cp", "/app/bin:/app/lib/*", "App"]
# ENTRYPOINT ["java", "-cp", "/app/bin:/app/lib/*", "App"]