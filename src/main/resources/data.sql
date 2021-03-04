USE `intelligent_express_cabinet`;

INSERT INTO roles(role_id, role_name, role_description)
SELECT 1,'ROLE_ADMIN','管理员'
FROM dual
WHERE NOT EXISTS(
    SELECT
           *
    FROM roles
    WHERE role_id=1
    );
INSERT INTO roles(role_id, role_name, role_description)
SELECT 2,'ROLE_USER','用户'
FROM dual
WHERE NOT EXISTS(
        SELECT
            *
        FROM roles
        WHERE role_id=2
    );
INSERT INTO roles(role_id, role_name, role_description)
SELECT 3,'ROLE_STAFF','专柜员'
FROM dual
WHERE NOT EXISTS(
        SELECT
            *
        FROM roles
        WHERE role_id=3
    );