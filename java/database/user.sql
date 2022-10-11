-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER final_capstone_owner
WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

CREATE USER final_capstone_appuser
WITH PASSWORD 'finalcapstone';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

CREATE USER LBrew
WITH PASSWORD 'brewer';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO LBrew;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO LBrew;

CREATE USER SBrew
WITH PASSWORD 'brewer';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO SBrew;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO SBrew;

CREATE USER JBrew
WITH PASSWORD 'brewer';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO JBrew;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO JBrew;

CREATE USER EBrew
WITH PASSWORD 'brewer';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO EBrew;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO EBrew;

