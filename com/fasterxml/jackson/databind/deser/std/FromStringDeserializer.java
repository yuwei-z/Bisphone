package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {

    public class Std extends FromStringDeserializer<Object> {
        protected final int _kind;

        protected Std(Class<?> cls, int i) {
            super(cls);
            this._kind = i;
        }

        protected Object _deserialize(String str, DeserializationContext deserializationContext) {
            int indexOf;
            switch (this._kind) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return new File(str);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return new URL(str);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return URI.create(str);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    try {
                        return deserializationContext.findClass(str);
                    } catch (Throwable e) {
                        throw deserializationContext.instantiationException(this._valueClass, ClassUtil.getRootCause(e));
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    return deserializationContext.getTypeFactory().constructFromCanonical(str);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    return Currency.getInstance(str);
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    return Pattern.compile(str);
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    indexOf = str.indexOf(95);
                    if (indexOf < 0) {
                        return new Locale(str);
                    }
                    String substring = str.substring(0, indexOf);
                    String substring2 = str.substring(indexOf + 1);
                    int indexOf2 = substring2.indexOf(95);
                    if (indexOf2 < 0) {
                        return new Locale(substring, substring2);
                    }
                    return new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    return Charset.forName(str);
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    return TimeZone.getTimeZone(str);
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    return InetAddress.getByName(str);
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    if (str.startsWith("[")) {
                        int lastIndexOf = str.lastIndexOf(93);
                        if (lastIndexOf == -1) {
                            throw new InvalidFormatException("Bracketed IPv6 address must contain closing bracket", str, InetSocketAddress.class);
                        }
                        indexOf = str.indexOf(58, lastIndexOf);
                        if (indexOf > -1) {
                            indexOf = Integer.parseInt(str.substring(indexOf + 1));
                        } else {
                            indexOf = 0;
                        }
                        return new InetSocketAddress(str.substring(0, lastIndexOf + 1), indexOf);
                    }
                    int indexOf3 = str.indexOf(58);
                    if (indexOf3 < 0 || str.indexOf(58, indexOf3 + 1) >= 0) {
                        return new InetSocketAddress(str, 0);
                    }
                    return new InetSocketAddress(str.substring(0, indexOf3), Integer.parseInt(str.substring(indexOf3 + 1)));
                default:
                    throw new IllegalArgumentException();
            }
        }

        protected Object _deserializeFromEmptyString() {
            if (this._kind == 3) {
                return URI.create("");
            }
            return super._deserializeFromEmptyString();
        }
    }

    protected abstract T _deserialize(String str, DeserializationContext deserializationContext);

    public static Class<?>[] types() {
        return new Class[]{File.class, URL.class, URI.class, Class.class, JavaType.class, Currency.class, Pattern.class, Locale.class, Charset.class, TimeZone.class, InetAddress.class, InetSocketAddress.class};
    }

    protected FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public static Std findDeserializer(Class<?> cls) {
        int i;
        if (cls == File.class) {
            i = 1;
        } else if (cls == URL.class) {
            i = 2;
        } else if (cls == URI.class) {
            i = 3;
        } else if (cls == Class.class) {
            i = 4;
        } else if (cls == JavaType.class) {
            i = 5;
        } else if (cls == Currency.class) {
            i = 6;
        } else if (cls == Pattern.class) {
            i = 7;
        } else if (cls == Locale.class) {
            i = 8;
        } else if (cls == Charset.class) {
            i = 9;
        } else if (cls == TimeZone.class) {
            i = 10;
        } else if (cls == InetAddress.class) {
            i = 11;
        } else if (cls != InetSocketAddress.class) {
            return null;
        } else {
            i = 12;
        }
        return new Std(cls, i);
    }

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Throwable th = null;
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            T deserialize = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return deserialize;
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
        }
        String valueAsString = jsonParser.getValueAsString();
        T _deserialize;
        if (valueAsString != null) {
            if (valueAsString.length() != 0) {
                String trim = valueAsString.trim();
                if (trim.length() != 0) {
                    try {
                        _deserialize = _deserialize(trim, deserializationContext);
                        if (_deserialize != null) {
                            return _deserialize;
                        }
                    } catch (IllegalArgumentException e) {
                        th = e;
                    }
                    valueAsString = "not a valid textual representation";
                    if (th != null) {
                        String message = th.getMessage();
                        if (message != null) {
                            valueAsString = valueAsString + ", problem: " + message;
                        }
                    }
                    JsonMappingException weirdStringException = deserializationContext.weirdStringException(trim, this._valueClass, valueAsString);
                    if (th != null) {
                        weirdStringException.initCause(th);
                    }
                    throw weirdStringException;
                }
            }
            return _deserializeFromEmptyString();
        } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
            _deserialize = jsonParser.getEmbeddedObject();
            if (_deserialize == null) {
                return null;
            }
            if (this._valueClass.isAssignableFrom(_deserialize.getClass())) {
                return _deserialize;
            }
            return _deserializeEmbedded(_deserialize, deserializationContext);
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected T _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) {
        throw deserializationContext.mappingException("Don't know how to convert embedded Object of type " + obj.getClass().getName() + " into " + this._valueClass.getName());
    }

    protected T _deserializeFromEmptyString() {
        return null;
    }
}
