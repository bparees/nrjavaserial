
/*
 * Copyright (C) 2010 Archie L. Cobbs. All rights reserved.
 *
 * $Id: FlowControlSuspendCommand.java 39 2011-03-22 17:21:53Z archie.cobbs $
 */

package gnu.io.rfc2217;

import static gnu.io.rfc2217.RFC2217.COM_PORT_OPTION;
import static gnu.io.rfc2217.RFC2217.FLOWCONTROL_SUSPEND;
import static gnu.io.rfc2217.RFC2217.SERVER_OFFSET;

/**
 * RFC 2217 {@code FLOWCONTROL-SUSPEND} command.
 *
 * @see <a href="http://tools.ietf.org/html/rfc2217">RFC 2217</a>
 */
public class FlowControlSuspendCommand extends ComPortCommand {

    /**
     * Decoding constructor.
     *
     * @param bytes encoded option starting with the {@code COM-PORT-OPTION} byte
     *   NullPointerException if {@code bytes} is null
     *   IllegalArgumentException if {@code bytes} has length != 3
     *   IllegalArgumentException if {@code bytes[0]} is not {@link RFC2217#COM_PORT_OPTION}
     *   IllegalArgumentException if {@code bytes[1]} is not {@link RFC2217#FLOWCONTROL_SUSPEND} (client or server)
     */
    public FlowControlSuspendCommand(int[] bytes) {
        super("FLOWCONTROL-SUSPEND", FLOWCONTROL_SUSPEND, bytes);
    }

    /**
     * Encoding constructor.
     *
     * @param client true for the client-to-server command, false for the server-to-client command
     */
    public FlowControlSuspendCommand(boolean client) {
        this(new int[] {
            COM_PORT_OPTION,
            client ? FLOWCONTROL_SUSPEND : FLOWCONTROL_SUSPEND + SERVER_OFFSET,
        });
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void visit(ComPortCommandSwitch sw) {
        sw.caseFlowControlSuspend(this);
    }

    @Override
    int getMinPayloadLength() {
        return 0;
    }

    @Override
    int getMaxPayloadLength() {
        return 0;
    }
}

