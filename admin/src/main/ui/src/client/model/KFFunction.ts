/**
 * API
 * API
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

import * as models from './models';

export interface KFFunction {
    id?: number;

    owner?: models.UserNamespace;

    name?: string;

    type?: KFFunction.TypeEnum;

    gitUrl?: string;

    namespace?: string;

    service?: string;

    deployment?: string;

    commitId?: string;

    ingressUrl?: string;

}
export namespace KFFunction {
    export enum TypeEnum {
        Generic = <any> 'generic',
        Nodejs = <any> 'nodejs',
        StaticSite = <any> 'static_site'
    }
}
