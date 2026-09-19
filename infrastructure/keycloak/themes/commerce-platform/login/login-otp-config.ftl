<#import "template.ftl" as layout>

<@layout.registrationLayout
    displayMessage=!messagesPerField.existsError('totp')
; section>

<#if section = "header">

    <div class="ecom-header">
        <h1 class="ecom-title">Configurar autenticador</h1>
        <p class="ecom-subtitle">
            Escaneie o QR Code abaixo com o Google Authenticator, Authy ou similar
        </p>
    </div>

<#elseif section = "form">

    <form
        id="kc-totp-settings-form"
        action="${url.loginAction}"
        method="post"
        class="ecom-form"
        novalidate
    >

        <div class="ecom-otp-setup">
            <img
                src="data:image/png;base64,${totp.totpSecretQrCode}"
                alt="QR Code do autenticador"
                class="ecom-otp-qr"
            />

            <div class="ecom-info">
                <strong>Não consegue escanear?</strong><br>
                Código manual:
                <code class="ecom-code">${totp.totpSecretEncoded}</code>
            </div>
        </div>

        <div class="ecom-field">
            <label for="totp">${msg("loginOtpOneTime")}</label>
            <input
                type="text"
                id="totp"
                name="totp"
                autofocus
                autocomplete="one-time-code"
                inputmode="numeric"
                pattern="[0-9]*"
                maxlength="6"
                placeholder="000000"
                class="ecom-input-otp"
                aria-invalid="<#if messagesPerField.existsError('totp')>true</#if>"
            />

            <#if messagesPerField.existsError('totp')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('totp'))?no_esc}
                </span>
            </#if>
        </div>

        <#if totp.otpCredentials?size gt 1>
            <div class="ecom-info">
                <strong>Dispositivos cadastrados:</strong>
                <ul class="ecom-device-list">
                    <#list totp.otpCredentials as credential>
                        <li>
                            <#if credential.label??>${credential.label}<#else>Dispositivo ${credential?counter}</#if>
                            <#if credential.userLabel??> — ${credential.userLabel}</#if>
                        </li>
                    </#list>
                </ul>
            </div>
        </#if>

        <div class="ecom-actions">
            <input
                type="submit"
                value="${msg('doSubmit')}"
                class="ecom-button"
            />

            <#if totp.otpCredentials?size gt 0>
                <input
                    type="submit"
                    name="cancel-aia"
                    value="${msg('doCancel')}"
                    class="ecom-button ecom-button--ghost"
                    formnovalidate
                />
            </#if>
        </div>

    </form>

</#if>

</@layout.registrationLayout>