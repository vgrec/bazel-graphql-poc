def _compiler(ctx):
    output_directory = ctx.actions.declare_directory("apollo")
    inputs = ctx.files.srcs

    output_files = [
        ctx.actions.declare_file("apollo/com/example/selections/LaunchListQuerySelections.kt"),
        ctx.actions.declare_file("apollo/com/example/adapter/LaunchListQuery_ResponseAdapter.kt"),
        ctx.actions.declare_file("apollo/com/example/type/GraphQLBoolean.kt"),
        ctx.actions.declare_file("apollo/com/example/type/Launch.kt"),
        ctx.actions.declare_file("apollo/com/example/type/LaunchConnection.kt"),
        ctx.actions.declare_file("apollo/com/example/type/GraphQLID.kt"),
        ctx.actions.declare_file("apollo/com/example/type/GraphQLFloat.kt"),
        ctx.actions.declare_file("apollo/com/example/type/Query.kt"),
        ctx.actions.declare_file("apollo/com/example/type/GraphQLString.kt"),
        ctx.actions.declare_file("apollo/com/example/type/GraphQLInt.kt"),
        ctx.actions.declare_file("apollo/com/example/LaunchListQuery.kt"),
    ]

    outputs = [output_directory]
    #    outputs.extend(output_files)

    args = ctx.actions.args()
    args.add(output_directory.path)
    args.add_all(ctx.files.srcs)

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
        "srcs": attr.label_list(
            allow_files = True,
            mandatory = True,
            allow_empty = False,
        ),
    },
    implementation = _compiler,
    output_to_genfiles = True,
)
