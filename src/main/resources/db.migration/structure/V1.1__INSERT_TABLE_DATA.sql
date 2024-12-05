INSERT INTO project_params (project_params_id, required_coverage_percentage, required_code_rating)
VALUES
    ('83c6c8f9-05c5-4973-8e56-55ec17faca22', 85, 'A'),
    ('785984c1-6761-4702-b8d5-8cb98ec473c2', 90, 'B');

INSERT INTO project_coverage_result (project_coverage_result_id, total_coverage)
VALUES
    ('dd14df9d-fa01-47f6-b01a-513f32a40e5b', 88.0),
    ('d6ccfc8b-0ae9-4768-a191-0aa8a1c1ba42', 80.0);

INSERT INTO code_analysis_result (code_analysis_result_id, actual_rating)
VALUES
    ('81287a3b-428e-4896-9a93-32c71c0b865e', 'A'),
    ('e8019e12-5de4-4954-a283-837ff10e59cc', 'B');

INSERT INTO project_results (project_results_id, status, project_coverage_result_id, code_analysis_result_id)
VALUES
    ('640381dc-0099-4e9e-aa37-c8350edb7f5e', 'Passed',
     'dd14df9d-fa01-47f6-b01a-513f32a40e5b', '81287a3b-428e-4896-9a93-32c71c0b865e'),
    ('8c3446f7-2422-4a7e-8566-9b6b017fee03', 'Failed',
     'd6ccfc8b-0ae9-4768-a191-0aa8a1c1ba42', 'e8019e12-5de4-4954-a283-837ff10e59cc');

INSERT INTO project (id, project_id, name, description, project_params_id, project_results_id)
VALUES
    ('4fbfd945-4cab-4ce0-bd59-99bccb1cfb5e', 'afc199e9-ab2a-4759-aa3b-fd5ca7306013', 'Project Alpha', 'Description for Project Alpha',
     '83c6c8f9-05c5-4973-8e56-55ec17faca22', '640381dc-0099-4e9e-aa37-c8350edb7f5e'),
    ('805edeba-87d1-4593-b83d-1335bf63ef48', 'afc199e9-ab2a-4759-eeee-fd5ca7306013', 'Project Beta', 'Description for Project Beta',
     '785984c1-6761-4702-b8d5-8cb98ec473c2', '8c3446f7-2422-4a7e-8566-9b6b017fee03');

INSERT INTO file_coverage_result (file_coverage_id, coverage_percentage, method_coverage_percentage)
VALUES
    ('9ce01882-7752-459f-8477-bd92918673d2', 95.0, 90.0),
    ('16dbab3c-38dd-4ff7-8126-81b44685aceb', 98.0, 94.0);

INSERT INTO method_coverage_result (method_coverage_id, coverage_percentage, coverage_result_id)
VALUES
    ('09564cc6-75ab-46be-8d1d-382586653ace', 85.0, '9ce01882-7752-459f-8477-bd92918673d2'),
    ('47a5d99d-d044-4056-b812-7001c3c67005', 95.0, '16dbab3c-38dd-4ff7-8126-81b44685aceb');

INSERT INTO method_statements (method_coverage_id, statement_key, statement_executed)
VALUES
    ('09564cc6-75ab-46be-8d1d-382586653ace', 1, true),
    ('47a5d99d-d044-4056-b812-7001c3c67005', 2, false);

INSERT INTO file (file_id, file_name, path, code_lines, project_id, coverage_result_id)
VALUES
    ('e49d9b78-c422-42ed-8c16-9c86dde9e558', 'Main.java', '/src/main/java/Main.java', 120,
     '4fbfd945-4cab-4ce0-bd59-99bccb1cfb5e', '9ce01882-7752-459f-8477-bd92918673d2'),
    ('31dc5ee2-f404-458c-a771-d76245692f34', 'App.java', '/src/app/App.java', 85,
     '805edeba-87d1-4593-b83d-1335bf63ef48', '16dbab3c-38dd-4ff7-8126-81b44685aceb');
