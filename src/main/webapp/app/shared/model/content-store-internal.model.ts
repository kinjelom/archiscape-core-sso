import dayjs from 'dayjs';

export interface IContentStoreInternal {
  id?: string;
  version?: number;
  insertDate?: string;
  fileName?: string | null;
  content?: string;
}

export const defaultValue: Readonly<IContentStoreInternal> = {};
