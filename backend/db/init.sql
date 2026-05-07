CREATE TABLE TASKS (
                       TASK_ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       TITLE VARCHAR2(255) NOT NULL,
                       DESCRIPTION VARCHAR2(500),
                       COMPLETED NUMBER(1) DEFAULT 0,
                       CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE TRIGGER TRG_TASKS_UPDATED
BEFORE UPDATE ON TASKS
                  FOR EACH ROW
BEGIN
    :NEW.UPDATED_AT := CURRENT_TIMESTAMP;
END;
/

CREATE OR REPLACE PACKAGE TASK_PKG AS

    PROCEDURE GET_ALL_TASKS(
        p_cursor OUT SYS_REFCURSOR
    );

    PROCEDURE GET_TASK_BY_ID(
        p_id IN NUMBER,
        p_cursor OUT SYS_REFCURSOR
    );

    PROCEDURE CREATE_TASK(
        p_title IN VARCHAR2,
        p_description IN VARCHAR2,
        p_completed IN NUMBER
    );

    PROCEDURE UPDATE_TASK(
        p_id IN NUMBER,
        p_title IN VARCHAR2,
        p_description IN VARCHAR2,
        p_completed IN NUMBER
    );

    PROCEDURE DELETE_TASK(
        p_id IN NUMBER
    );

END TASK_PKG;
/

CREATE OR REPLACE PACKAGE BODY TASK_PKG AS

    PROCEDURE GET_ALL_TASKS(
        p_cursor OUT SYS_REFCURSOR
    ) AS
BEGIN

OPEN p_cursor FOR
SELECT *
FROM TASKS
ORDER BY TASK_ID;

END;

    PROCEDURE GET_TASK_BY_ID(
        p_id IN NUMBER,
        p_cursor OUT SYS_REFCURSOR
    ) AS
BEGIN

OPEN p_cursor FOR
SELECT *
FROM TASKS
WHERE TASK_ID = p_id;

END;

    PROCEDURE CREATE_TASK(
        p_title IN VARCHAR2,
        p_description IN VARCHAR2,
        p_completed IN NUMBER
    ) AS
BEGIN

INSERT INTO TASKS (
    TITLE,
    DESCRIPTION,
    COMPLETED
)
VALUES (
           p_title,
           p_description,
           p_completed
       );

COMMIT;

END;

    PROCEDURE UPDATE_TASK(
        p_id IN NUMBER,
        p_title IN VARCHAR2,
        p_description IN VARCHAR2,
        p_completed IN NUMBER
    ) AS
BEGIN

UPDATE TASKS
SET
    TITLE = p_title,
    DESCRIPTION = p_description,
    COMPLETED = p_completed
WHERE TASK_ID = p_id;

COMMIT;

END;

    PROCEDURE DELETE_TASK(
        p_id IN NUMBER
    ) AS
BEGIN

DELETE FROM TASKS
WHERE TASK_ID = p_id;

COMMIT;

END;

END TASK_PKG;
/