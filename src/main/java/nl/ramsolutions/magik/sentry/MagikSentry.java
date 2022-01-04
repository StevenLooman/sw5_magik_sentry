package nl.ramsolutions.magik.sentry;

import com.gesmallworld.magik.commons.interop.annotations.ExemplarInstance;
import com.gesmallworld.magik.commons.interop.annotations.MagikExemplar;
import com.gesmallworld.magik.commons.interop.annotations.MagikMethod;
import com.gesmallworld.magik.commons.interop.annotations.Name;
import com.gesmallworld.magik.commons.interop.annotations.Optional;
import com.gesmallworld.magik.interop.MagikInteropUtils;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.protocol.Message;

/**
 * Sentry wrapper.
 *
 */
@MagikExemplar(@Name(value = "magik_sentry"))
public final class MagikSentry {

    private static final MagikSentry INSTANCE = new MagikSentry();

    /**
     * Private constructor, singleton via `initialiseExemplar`.
     */
    private MagikSentry() {
    }

    /**
     * Get exemplar instance.
     * @return Exemplar instance.
     */
    @ExemplarInstance
    public static Object initialiseExemplar() {
        return MagikSentry.INSTANCE;
    }

    /**
     * `initialize()` magik method, initialize Sentry.
     * @param self Self.
     * @param magikDsn String: DSN.
     * @param magikTracesSampleRate Double: Traces sample rate.
     * @param magikDebug Boolean: Debug.
     * @return Null.
     */
    @MagikMethod("initialize()")
    public static Object initialize(
            final Object self,
            final Object magikDsn,
            final @Optional Object magikTracesSampleRate,
            final @Optional Object magikDebug) {
        final String dsn = MagikInteropUtils.fromMagikString(magikDsn);
        final double tracesSampleRate = MagikInteropUtils.fromMagikDouble(magikTracesSampleRate, 1.0);
        final boolean debug = MagikInteropUtils.fromMagikBoolean(magikDebug, false);

        final SentryOptions options = new SentryOptions();
        options.setDebug(debug);
        options.setDsn(dsn);
        options.setTracesSampleRate(tracesSampleRate);

        Sentry.init(options);
        System.out.println("Sentry initialized");

        return null;
    }

    /**
     * `capture_event()` magik method, capture an event.
     * @param self Self.
     * @param magikLevel String: Level, one of: DEBUG, INFO, WARNING, ERROR, FATAL.
     * @param magikMessage String: Message to send.
     * @return Null.
     */
    @MagikMethod("capture_event()")
    public static Object captureEvent(final Object self, final Object magikLevel, final Object magikMessage) {
        final String level = MagikInteropUtils.fromMagikString(magikLevel);
        final String message = MagikInteropUtils.fromMagikString(magikMessage);

        final SentryEvent event = new SentryEvent();

        final SentryLevel sentryLevel = SentryLevel.valueOf(level.toUpperCase());
        event.setLevel(sentryLevel);

        final Message sentryMessage = new Message();
        sentryMessage.setMessage(message);
        event.setMessage(sentryMessage);

        Sentry.captureEvent(event);

        return null;
    }

}
