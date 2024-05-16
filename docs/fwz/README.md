# Folio Workflow Zip Format

The **Folio Workflow Zip** format (**FWZ**) is a **ZIP** format containing files that represent a **Workflow**.
This format is intended to be used for importing, exporting, or otherwise transporting a **Workflow**.

This documentation describes the general structure of the **FWZ** format.
The structure of an individual **Workflow** is referenced here but the details for creating a **Workflow** are not described here.

The directory structure of an uncompressed **FWZ** is dependent on the type of **Triggering Event** or **Scheduled Event** the **Workflow** represents.
The different types of **Workflows** share a common base structure, described under the [Base Directory Structure](#base-directory-structure) section.

This format stores the **Workflow** in individual **JSON** files and other miscellaneous file types depending on need.
Most of the structure of the files directly matches how it is stored in the `mod-workflow` database, with some exceptions.

One of those exceptions is the `ScriptTask`.
A `ScriptTask` stores the actual contents of the `code` in a separate file in a sub-directory under the `nodes/` directory path.
On import of an **FWZ** file, this script file is stored as a single value in the `code`, replacing the file name that was previously stored in the value of the `code` property.
On export into an **FWZ** file, this `code` is converted into a script file and the file is named base on the `ScriptTask` name with an extension based on the scripting language in use.


## Base Directory Structure

  1. [`nodes/`](#nodes-sub-directory-structure)
  2. [`fwz.json`](#fwzjson-file)
  3. [`setup.json`](#setupjson-file)
  4. [`workflow.json`](#workflowjson-file)


### Nodes Sub-directory Structure

The `nodes/` sub-directory contains most of the **Workflow** **Nodes**.
These files generally represent the **Tasks** or the **Delegates**.

Each **Node** within the `nodes/` sub-directory is a **JSON** file.

The following properties are supported for all **Nodes**:

| Property Name | Example | Description |
| :---          | :---    | :---        |
| `description` | `"Start the Workflow"` | A description of the **Node**, usually represented as a complete sentence. |
| `deserializeAs` | `StartEvent` | The **Node** type. This is formatted to represent the **Java** class name. |
| `name` | `"Start"` | The name of the **Node**. |

The `nodes/` sub-directory itself may contain additional sub-directories, such as the `js/` directory used by `ScriptTask` **Nodes**.

The [Types of Nodes section](#types-of-nodes) below goes into further details regarding the **Nodes**.


### fwz.json File

The `fwz.json` file contains data about the **FWZ** format itself.
This neither describes the **Workflow** nor is it part of the **Workflow**.

The following properties are supported:

| Property Name | Example | Description |
| :---          | :---    | :---        |
| `version`     | `'1.0.0'` | A version number string representing the version of the **FWZ** format standard that this **FWZ** file is following. |

\* Only a single version is supported at this time.


### setup.json File

The `setup.json` file provides initial configuration.

This file is usually an empty object `{}`.


### workflow.json File

The `workflow.json` file is the main file representing the **Workflow**.
All of the **Nodes** are designated within this file via their **UUID**.

The following properties are supported:

| Property Name | Example | Description |
| :---          | :---    | :---        |
| `active` | `false` | A boolean representing whether or not the **Workflow** is active. This may be changed during runtime. |
| `deploymentId` | `e4b0906c-298d-4b3a-b9a4-54d0dcdf58a5` | A **UUID** representing the identifier of the **Workflow** deployed in the **Workflow Engine**.|
| `description` | `"This is my workflow."` | A description of the **Workflow**, usually represented as a complete sentence. |
| `name` | `"My Workflow"` | The name of the **Workflow**. |
| `historyTimeToLive` | `90` | A, [possibly Camunda-specific](https://docs.camunda.org/manual/7.21/reference/bpmn20/custom-extensions/extension-attributes/#historytimetolive), number designating the time in days to preserve the history. |
| `initialContext` | `{}` | An object representing additional properties, added to the **Workflow**. |
| `nodes` | `[]` | An array of **Nodes** represented as a string that operates in a top-down order. |
| `setup.asyncAfter`  | `false` | A, [possibly Camunda-specific](https://docs.camunda.org/manual/7.21/reference/bpmn20/custom-extensions/extension-attributes/#asyncafter), boolean designating [asynchronous continuation](https://docs.camunda.org/manual/7.21/user-guide/process-engine/transactions-in-processes/#asynchronous-continuations) after an activity. |
| `setup.asyncBefore` | `true` | A, [possibly Camunda-specific](https://docs.camunda.org/manual/7.21/reference/bpmn20/custom-extensions/extension-attributes/#asyncbefore), boolean designating [asynchronous continuation](https://docs.camunda.org/manual/7.21/user-guide/process-engine/transactions-in-processes/#asynchronous-continuations) before an activity. |
| `versionTag` | `"1.0.0"` | A version number string representing the version of the **Workflow**. |

The following is an example `workflow.json` file:
```json
{
  "id": "9908e4c0-6c48-4b2f-a487-a0398f74549f",
  "name": "Quick JavaScript Test",
  "description": "Test that JavaScript works as a ScriptTask by printing a simple message.",
  "versionTag": "1.0",
  "historyTimeToLive": 90,
  "deploymentId": null,
  "active": false,
  "setup": {
    "asyncBefore": false,
    "asyncAfter": false
  },
  "nodes": [
    "{{mod-workflow}}/startEvent/613f670b-ae88-462a-a19a-bb2af8b65279",
    "{{mod-workflow}}/scriptTask/76ddfc54-0d05-42c2-a0a7-4efa16f63f66",
    "{{mod-workflow}}/endEvent/b9b00b03-89f1-4a02-a8fb-e0ea0e6eb021"
  ],
  "initialContext": {}
}
```


## Event Based Workflow and its Directory Structure

This directory structure extends the [Base Directory Structure](#base-directory-structure) providing additional requirements for executing the **Workflow** on some event.

An event based **Workflow** is a type of **Triggering Event**.

1. `triggers/startTrigger.json`

The `startTrigger.json` file designates how the **Workflow** is started.
This is a type of **Event** with a property `type` of `MESSAGE_CORRELATE`.

The event based **Workflow** requires a `start.json` **Node** of type `MESSAGE_CORRELATE`.

There are different sub-types, such as `MESSAGE_CORRELATE`.
A `MESSAGE_CORRELATE` is essentially a **REST** request defined by some `method`, such as an **HTTP** `POST`.
A `MESSAGE_CORRELATE` listens on some `pathPattern` on the **Workflow Engine** server.

The following is an example `triggers/startTrigger.json` file:
```json
{
  "id": "41604db9-e588-40c3-8798-afb348a61230",
  "name": "Quick Javascript Test Start Trigger",
  "description": "A REST POST end point for running the Workflow.",
  "type": "MESSAGE_CORRELATE",
  "method": "POST",
  "deserializeAs": "EventTrigger",
  "pathPattern": "/events/workflow/quick-js-test/start"
}
```

The `nodes/start.json` for an event based **Workflow** will be roughly identical to the `triggers/startTrigger.json` file (they will have a different **UUID**).


## Time Based Workflow and its Directory Structure

This directory structure extends the [Base Directory Structure](#base-directory-structure) providing additional requirements for executing the **Workflow** on some time based schedule.

A time based **Workflow** represents a type of **Scheduled Event**.

The time based **Workflows** do not differ in directory structure from the [Base Directory Structure](#base-directory-structure).

The time based **Workflow** requires a `start.json` **Node** of type `SCHEDULED`.

The time based **Workflow** has the property `expression` defined similar to a **CRON Job** format for `mod-camunda`.
However, there is an additional value not defined by the standard **CRON Job**.
In many cases, this additional sixth value can be a single question mark `?`.
The [Camunda time cycle documenation](https://docs.camunda.org/manual/7.21/reference/bpmn20/events/timer-events/#time-cycle) and the [more technical Camunda CRON documentation](https://docs.camunda.org/javadoc/camunda-bpm-platform/7.21/org/camunda/bpm/engine/impl/calendar/CronExpression.html) further describes this.

The time used is represented in the time zone that the `mod-camunda` server is configured to run as.
This may not always be **UTC**.

The following is an example `nodes/start.json` file:
```json
{
  "id": "27a42f0f-e301-48af-a4f5-7c21a3c0718f",
  "name": "Start",
  "description": "",
  "type": "SCHEDULED",
  "deserializeAs": "StartEvent",
  "expression": "0 0 9 * * ?"
}
```


## Types of Nodes

A [list of supported **Nodes**](https://github.com/folio-org/mod-workflow/blob/master/components/src/main/java/org/folio/rest/workflow/model/Node.java) may also be found in the [`mod-workflow` project repository](https://github.com/folio-org/mod-workflow/tree/master).

Each of these types of **Nodes** may be used in the `deserializeAs` property to designate the **Node** type of a given file.

  - **`CompressFileTask`**: A Task for compressing some file or directory.
    - `container`: The container, if any, to store the file before compression, such as `none` or `tar`. This is useful for compressing directries.
    - `destination`: The destination file path to compress the file as.
    - `format`: The compression format to use, such as `bzip2`, `gzip`, and `zip`.
    - `source`: The source file path to compress.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`Condition`**: A fork in the **Workflow** for operating on a given pathway based on the specified conditions.
    - `answer`: A `yes` or `no` boolean-like string representing the path in which this `condition` acts on.
    - `expression`: A conditional expression that the `condition` acts on. This may be **Workflow Engine** specific. The [Camunda-specific Condition event](https://docs.camunda.org/manual/7.21/reference/bpmn20/events/conditional-events/#condition) has more details.

  - **`DatabaseConnectionTask`**: Establish a connection to a database.
    - `url`: The **URL** to the database. This should include any database-specific **URL** connection parameters.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Credentials Properties](#common-credentials-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`DatabaseDisconnectTask`**: Close an established connection to a database.
    - `designation`: The name, or designation, of the particular database connection to close.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`DatabaseQueryTask`**: Execute a query using a designated database connection.
    - `designation`: The name, or designation, of the particular database connection to close.
    - `includeHeader`: A boolean representing whether or not to include the columns as a header row in the results file.
    - `outputPath`: A file path to store the database results in.
    - `query`: The query to execute.
    - `resultType`: The file format in which the database results are stored in.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`DirectoryTask`**: Perform an operation against a directory, such as creating or deleting the directory.
    - `action`: The action to perform on the directory, such as `read_next`, `delete_next`, `list`, or `write`.
    - `path`: The file path to the directory, including the directory itself.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`EmailTask`**: Send an e-mail.
    - `attachmentPath`: The path to a single attachment to add to the e-mail. Omit this if there is nothing to attach.
    - `includeAttachment`: A boolean representing whether or not to attach a file to the e-mail.
    - `mailFrom`: The e-mail address of the sender.
    - `mailMarkup`: The content of the e-mail as HTML Markup. The e-mail may simultaneously contain both HTML markup and plain text.
    - `mailSubject`: The subject of the e-mail.
    - `mailText`: The content of the e-mail as plain text. The e-mail may simultaneously contain both HTML markup and plain text.
    - `mailTo`: The e-mail address of the recipient.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`EndEvent`**: Designate the end of a **Workflow**.

  - **`EventSubprocess`**: A sub-process that executes within a workflow and may conditionally be interruptible. The [Camunda sub-process documentation](https://docs.camunda.org/manual/7.21/reference/bpmn20/subprocesses/event-subprocess/) has more details.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).

  - **`ExclusiveGateway`**: An `XOR` fork (or join) in the process where only a single pathway must be followed. The [Camunda exclusive **Gateway** documentation](https://docs.camunda.org/manual/7.21/reference/bpmn20/gateways/exclusive-gateway/) has more details.
    - This has the [Common Gateway Properties](#common-gateway-properties).

  - **`FileTask`**: Perform an operation against a file, such as creating or deleting the file.
    - `line`: This is a number representing the line to use for line-specific actions.
    - `op`: The action to perform on the file, such as `list`, `read`, `write`, `copy`, `move`, `delete`, `line_count`, `read_line`, `push`, or `pop`.
    - `path`: The file path to the file. The directory path to the file is expected to already exist.
    - `target`: For `copy`, `move`, or any action that requires an additional file path or directory path to function.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`FtpTask`**: Perform an **FTP** or **SFTP** operation using some file.
    - `destinationPath`: The destination path to a file or directory on the server side of the connection.
    - `host`: The **URL** host part of the server to connect to.
    - `op`: The action to perform on the file, such as `get` or `put`.
    - `originPath`: The source path to a file or directory on the client side of the connection.
    - `port`: The **URL** port part of the server to connect to.
    - `scheme`: The **URL** scheme part of the server to connect to.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Credentials Properties](#common-credentials-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`InclusiveGateway`**: A fork (or join) in the process where each pathway is followed depending on a given **Condition** **Node**. The [Camunda inclusive **Gateway** documentation](https://docs.camunda.org/manual/7.21/reference/bpmn20/gateways/inclusive-gateway/) has more details.
    - This has the [Common Gateway Properties](#common-gateway-properties).

  - **`MoveToLastGateway`**: Part of a **Gateway** for facilitating the joining of the **Gateway** that moves to the last **Gateway**.
    - This has the [Common Gateway Properties](#common-gateway-properties).

  - **`MoveToNode`**: Part of a **Gateway** for facilitating the joining of a single **Gateway**.
    - `gatewayId`: The identifier of the gateway being joined. This is usually a **UUID**.
    - This has the [Common Gateway Properties](#common-gateway-properties).

  - **`ParallelGateway`**: A fork (or join) in the process where every *path* is followed. The [Camunda parallel **Gateway** documentation](https://docs.camunda.org/manual/7.21/reference/bpmn20/gateways/parallel-gateway/) has more details.
    - This has the [Common Gateway Properties](#common-gateway-properties).

  - **`ReceiveTask`**: Wait for the arrival of a message.
    - `message`: The message to wait for.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).

  - **`RequestTask`**:
    - `request`: The **HTTP** request to perform as an object.
      - `accept`: The content-type to expect from the **HTTP** response.
      - `bodyTemplate`: The content of the **HTTP** request, with template support.
      - `contentType`: The content-type header to send.
      - `iterable`: A boolean representing whether or not an iteration is in use.
      - `iterableKey`: The key used to represent the `iterable`.
      - `method`: The **HTTP** method.
      - `responseKey`: The key used to represent the response.
      - `url`: The **URL** of the request.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`ScriptTask`**: Execution code using a supported language.
    - `code`: The file, within the **Workflow**, representing the code to execute.
    - `resultVariable` A single variable returned by the executed code.
    - `scriptFormat`: The scripting language to use, such as `groovy`, `java`, `javascript`, `ruby`, or `python`.
      - For `groovy`, the scripts are stored in `groovy` sub-directory.
      - For `java`, the scripts are stored in `java` sub-directory.
      - For `javascript`, the scripts are stored in `js` sub-directory.
      - For `ruby`, the scripts are stored in `rb` sub-directory.
      - For `python`, the scripts are stored in `py` sub-directory.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).
    - This has the [Common Variable Properties](#common-variable-properties).

  - **`StartEvent`**: Designate the start of a **Workflow**.
    - `expression`: An expression used by a given start event type. This is different between types.
      - A `MESSAGE_CORRELATION` start event type uses a relative **URL** path that is relative to the `mod-camunda` server path.
      - A `SCHEDULED` start event type use the [Camunda time cycle documenation](https://docs.camunda.org/manual/7.21/reference/bpmn20/events/timer-events/#time-cycle) is relevant for the `SCHEDULED`.
    - `interrupting`: A boolean designating the interruptibility of a `SIGNAL` start event type.
    - `type`: The type of start event, such as: `MESSAGE_CORRELATION`, `SCHEDULED`, `SIGNAL`, or `NONE`.

  - **`Subprocess`**: Is a collection of **Nodes** in a **Workflow** that may potentially also act as a loop.
    - `loopRef`: The identifier used to reference the loop when acting as a loop.
    - `nodes`: Additional **Nodes** to be operated within this sub-process.
    - `type`: The type of sub-process, such as `embedded` or `transaction`.
    - This has the [Common Asynchronous Properties](#common-asynchronous-properties).

### Common Asynchronous Properties
  - `asyncAfter`: A, [possibly Camunda-specific](https://docs.camunda.org/manual/7.21/reference/bpmn20/custom-extensions/extension-attributes/#asyncafter), boolean designating [asynchronous continuation](https://docs.camunda.org/manual/7.21/user-guide/process-engine/transactions-in-processes/#asynchronous-continuations) after an activity.
  - `asyncBefore`: A, [possibly Camunda-specific](https://docs.camunda.org/manual/7.21/reference/bpmn20/custom-extensions/extension-attributes/#asyncbefore), boolean designating [asynchronous continuation](https://docs.camunda.org/manual/7.21/user-guide/process-engine/transactions-in-processes/#asynchronous-continuations) before an activity.

### Common Credentials Properties
  - `password`: The password associated with the specified user name.
  - `username`: The user name to login as.

### Common Gateway Properties
  - `direction`: The direction in which the **Gateway** operates, such as `unspecified`, `converging`, `diverging`, or `mixed`.
  - `nodes`: Additional **Nodes** to be operated within this **Gateway**.

### Common Variable Properties
  - `inputVariables`: An array of variables passed to the **Node**.
  - `outputVariable`: A single variable returned by the processing of the **Node**.


## Glossary

This glossary is intended to describe terms in the context of this documentation, `mod-workflow`, or `mod-camunda`.

  - **Delegate**: A discrete type of **Node** that gives control over to something else. This is often used to execute code such as `javascript`. Some **Tasks** also delegate control, such as a `ScriptTask` which delegates control over to a scripting engine like `javascript`. The [Camunda delegate documentation](https://docs.camunda.org/manual/7.21/user-guide/process-engine/delegation-code/) has more details.

  - **Engine**: The service that performs the actual execution. The `mod-camunda` project is an example of an **Engine**.

  - **Event**: This generally refers to a **BPMN** event such as those described in the [Camunda event documentation](https://docs.camunda.org/manual/7.21/reference/bpmn20/#events).

  - **FOLIO**: An abbreviation for "*(the) Future of Libraries is Open*".

  - **FTP**: An abbreviation for "*File Transfer Protocol*".

  - **FWZ**: An abbreviation for "*FOLIO Workflow Zip*".

  - **Gateway**: A fork in the **Workflow** where two or more paths may be taken. The **Gateway** **Nodes** may be Camunda-specific in design.

  - **HTTP**: An abbreviation for "*Hypertext Transfer Protocol*".

  - **JSON**: An abbreviation for "*JavaScript Object Notation*".

  - **Node**: An **Event**, **Task**, or a collection of **Nodes**. Each **Node** has a discrete type that further defines what it is and how it operates. A `startEvent`, for example, is a **Node** that is an **Event** type that is used to start a given **Workflow**.

  - **Scheduled Event**: A type of **Event** that is started based on some interval in time.

  - **SFTP**: An abbreviation for *Secure File Transfer Protocol*.

  - **Task**: A **Task** is a type of **Node** that performs the actual work in the **Workflow**. There are many different types of **Tasks**. The [Camunda task documentation](https://docs.camunda.org/manual/7.21/reference/bpmn20/tasks/) has more details.

  - **Triggering Event**: A type of **Event** that is either started through an end point or a trigger, such as by some **FOLIO** service making a **HTTP** request.

  - **URL**:  An abbreviation for "*Uniform Resource Locator*".

  - **UUID**: An abbreviation for "*Universal Unique Identifier*". These are used as a common practice for uniquely representing many different aspects of a **Workflow**.

  - **Workflow**: An ordered list of **Nodes** that contain or represent the actions or operations to perform. A **Workflow** is often designed to perform a single task through a series of one or more steps.

  - **Workflow Engine**: This is the **Engine**, such as `mod-camunda`, that performs the actual execution of a given **Workflow**.

  - **ZIP**: A compressed directory of files. This is generally used to loosely refer to any compressed data, such as **GZIP** data. This traditionally refers to the archive file format called **ZIP**.
