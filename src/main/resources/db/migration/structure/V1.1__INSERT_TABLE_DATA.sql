INSERT INTO
    project (project_id, name, description, required_coverage_percentage, required_code_rating, status)
VALUES
    ('525dbe0d-4987-4304-b39d-43840f4c18ee', 'Project Alpha', 'Description for Project Alpha', 85, 'A', null),
    ('f69e7e4d-47f9-4e08-86e1-667f558051bd', 'Project Beta', 'Description for Project Beta', 90, 'B', null);

INSERT INTO
    project_params (params_id, project_id, required_coverage_percentage, required_code_rating)
VALUES
    ('3e583c6d-999c-4864-944e-6db94cf5b153', '525dbe0d-4987-4304-b39d-43840f4c18ee', 85, 'A'),
    ('f0217cd5-4ffe-48c5-a7fc-cbe9edb44f2e', 'f69e7e4d-47f9-4e08-86e1-667f558051bd', 90, 'B');

INSERT INTO
    project_results (results_id, project_id, status, actual_coverage, required_coverage, actual_rating, expected_rating)
VALUES
    ('baf6d674-81c4-429f-9664-839538a290f8', '525dbe0d-4987-4304-b39d-43840f4c18ee', 'PASSED', 88, 85, 'A', 'A'),
    ('36feb4bf-5885-40f1-98e1-29d03d7e7b4b', 'f69e7e4d-47f9-4e08-86e1-667f558051bd', 'FAILED', 80, 90, 'B', 'C');

INSERT INTO
    file (file_id, project_id, file_name, path, code_lines)
VALUES
    ('e49d9b78-c422-42ed-8c16-9c86dde9e558', '525dbe0d-4987-4304-b39d-43840f4c18ee', 'Main.java', '/src/main/java/Main.java', 120),
    ('31dc5ee2-f404-458c-a771-d76245692f34', 'f69e7e4d-47f9-4e08-86e1-667f558051bd', 'App.java', '/src/app/App.js', 85);

INSERT INTO
    file_coverage_result (file_coverage_id, file_id, all_statements)
VALUES
    ('9ce01882-7752-459f-8477-bd92918673d2', 'e49d9b78-c422-42ed-8c16-9c86dde9e558', '[{"line": 1, "covered": true}, {"line": 2, "covered": false}]'::JSONB),
    ('16dbab3c-38dd-4ff7-8126-81b44685aceb', '31dc5ee2-f404-458c-a771-d76245692f34', '[{"line": 1, "covered": true}, {"line": 2, "covered": true}]'::JSONB);

INSERT INTO
    method_coverage_result (method_coverage_id, file_coverage_id, statements)
VALUES
    ('bbbbbbbb', '9999999999', '{"1": true, "2": false}'::JSONB),
    ('cccccccc-', 'aaaaaaaaaa', '{"1": true, "2": true}'::JSONB);