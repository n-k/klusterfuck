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

export interface ServicePort {
    name?: string;

    nodePort?: number;

    port?: number;

    protocol?: string;

    targetPort?: models.IntOrString;

}