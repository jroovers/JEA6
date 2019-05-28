import { KweetService } from '../services/kweet.service';
import { Subject, Observable } from 'rxjs';
import { Kweet } from './kweet';
import { ObserveOnSubscriber } from 'rxjs/internal/operators/observeOn';

export class WebsocketClient {
    private webSocket = null;
    private protocol: string = "";
    private hostname: string = "";
    private port: number = 0;
    private endpoint: string = "";

    public currentKweetSubject: Observable<Kweet>;

    constructor(
        protocol: string,
        hostname: string,
        port: number,
        endpoint: string
    ) {
        this.protocol = protocol;
        this.hostname = hostname;
        this.port = port;
        this.endpoint = endpoint;
    }

    getServerUrl() {
        return this.protocol + "://" + this.hostname + ":" + this.port + this.endpoint;
    }

    connect() {
        try {
            this.webSocket = new WebSocket(this.getServerUrl());
            // 
            // Implement WebSocket event handlers!
            //
            this.webSocket.onopen = function (event) {
                console.log('onopen::' + JSON.stringify(event, null, 4));
            }
            this.webSocket.onmessage = function (event) {
                let kweet: Kweet = { ...event.data };
                this.currentKweetSubject.next(kweet);
                console.log('onmessage::' + kweet);
            }
            this.webSocket.onclose = function (event) {
                console.log('onclose::' + JSON.stringify(event, null, 4));
            }
            this.webSocket.onerror = function (event) {
                console.log('onerror::' + JSON.stringify(event, null, 4));
            }

        } catch (exception) {
            console.error(exception);
        }
    }

    getStatus() {
        return this.webSocket.readyState;
    }

    getWebSocket(): WebSocket {
        return this.webSocket;
    }

    public GetInstanceStatus(): Observable<Kweet> {
        return Observable.create(observer => {
            this.webSocket.onmessage = (event) => {
                let kweet: Kweet = { ...event.data };
                observer.next(kweet);
            };
        })
    }

    send(message) {

        if (this.webSocket.readyState == WebSocket.OPEN) {
            this.webSocket.send(message);

        } else {
            console.error('webSocket is not open. readyState=' + this.webSocket.readyState);
        }
    }

    disconnect() {
        if (this.webSocket.readyState == WebSocket.OPEN) {
            this.webSocket.close();

        } else {
            console.error('webSocket is not open. readyState=' + this.webSocket.readyState);
        }
    }
}
