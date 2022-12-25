def _compiler(ctx):
    output_directory = ctx.actions.declare_directory("apollo")
    output_srcjar = ctx.actions.declare_file("sources.srcjar")

    inputs = [ctx.file.query_file, ctx.file.schema_file]
    outputs = [output_directory, output_srcjar]

    args = ctx.actions.args()
    args.add(output_directory.path)
    args.add(output_srcjar.path)
    args.add(ctx.file.query_file)
    args.add(ctx.file.schema_file)

    ctx.actions.run(
        executable = ctx.executable.code_generator,
        arguments = [args],
        inputs = inputs,
        outputs = outputs,
    )

    return [DefaultInfo(files = depset(outputs))]

compiler = rule(
    attrs = {
        "code_generator": attr.label(
            cfg = "exec",
            default = ":code_generator",
            executable = True,
        ),
        "query_file": attr.label(
            allow_single_file = True,
            mandatory = True,
        ),
        "schema_file": attr.label(
            allow_single_file = True,
            mandatory = True,
        ),
    },
    implementation = _compiler,
    output_to_genfiles = True,
)
