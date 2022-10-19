def _compiler(ctx):
    out = ctx.actions.declare_directory("apollo")
    inputs = ctx.files.srcs

    args = ctx.actions.args()
    args.add(out.path)
    args.add_all(ctx.files.srcs)

    ctx.actions.run(
        executable = ctx.executable.code_generator,
        arguments = [args],
        inputs = inputs,
        outputs = [out],
    )

    return [DefaultInfo(files = depset([out]))]

compiler = rule(
    attrs = {
        "code_generator": attr.label(
            cfg = "exec",
            default = ":code_generator",
            executable = True,
        ),
        "srcs": attr.label_list(
            allow_files = True,
            mandatory = True,
            allow_empty = False,
        ),
    },
    implementation = _compiler,
    output_to_genfiles = True,
)
